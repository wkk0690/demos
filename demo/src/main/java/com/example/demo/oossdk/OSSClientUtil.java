package com.example.demo.oossdk;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * 阿里云 OSS工具类
 *
 * @author Monkey
 * @version 1.0
 * @date 2017年9月30日下午3:38:09
 */
@Controller
public class OSSClientUtil {

    public static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);
    // endpoint
    private String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // accessKey
    private String accessKeyId = "LTAIRg5fsrEnhczN";
    private String accessKeySecret = "Z4xJziTdUmEw2YnMpOpUSpK8OhRM9r";
    // 存储空间
    private String bucketName = "aliyunoostest124";
    // 文件夹
    private String filedir = "aa/";

    private OSSClient ossClient;

    public static void main(String[] args) throws Exception {
        OSSClientUtil ossClient = new OSSClientUtil();

        String pic = "D:\\workspace\\oos-sdk\\src\\main\\java\\com\\example\\oossdk\\demo\\322019.jpg";
        File file = new File(pic);
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile mockMultipartFile = new MockMultipartFile(file.getName(), inputStream);

        //作为借口参数是MultipartFile
        String name = ossClient.uploadImg2Oss(mockMultipartFile);
        String imgUrl = ossClient.getImgUrl(name);
        System.out.println(imgUrl);

        //通过url上传
//        ossClient.uploadImg2Oss(pic);
//        String imgUrl = ossClient.getImgUrl("322019.jpg");
//        System.out.println(imgUrl);
    }


    public OSSClientUtil() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 初始化
     */
    public void init() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 通过图片名称获取图片访问路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(this.filedir + split[split.length - 1]);
        }
        return null;
    }

    /**
     * 上传图片
     *
     * @param url
     */
    public void uploadImg2Oss(String url) throws RuntimeException {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            this.uploadFile2OSS(fin, split[split.length - 1]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("图片上传失败");
        }
    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     * @throws RuntimeException
     */
    public String uploadImg2Oss(MultipartFile file) throws RuntimeException {
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new RuntimeException("上传图片大小不能超过10M！");
        }

        //为了方便测试吧这里改成了 getName()
        //String originalFilename = file.getOriginalFilename();
        String originalFilename = file.getName();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name);
            return name;
        } catch (Exception e) {
            throw new RuntimeException("图片上传失败");
        }
    }

    /**
     * 上传到OSS服务器 如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10

        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}

