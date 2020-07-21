package com.example.demo.oossdk;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 0X574B4B
 * @create 2018-10-09 13:33
 * @descriptions <p></p >
 */
public class demo {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "LTAIpjazpiiGqj7B";
    String accessKeySecret = "ekdx4Sh7JUjN5HsC6TBamRDDdWd2L5";
    /**
     * 创建存储空间
     */
    @Test
    public void demo1(){
        String bucketName = "sherlock002";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 创建存储空间。
        ossClient.createBucket(bucketName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 上传文件
     */
    @Test
    public void demo2(){
        String bucketName = "sherlock002";
        String objectName = "aa";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        String content = "Hello OSS";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 下载文件
     */
    @Test
    public void demo3() throws IOException {
        String bucketName = "sherlock002";
        String objectName = "aa";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                System.out.println("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 删除文件
     */
    @Test
    public void demo4(){
        String bucketName = "sherlock002";
        String objectName = "aa";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 上传文件流
     */
    @Test
    public void demo5() throws FileNotFoundException {
        String pre = "https://";
        String url = "oss-cn-beijing.aliyuncs.com/";
        String bucketName = "sherlock002";
        String fileName = "322019.jpg";
        String filedir = "aa/";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream = new FileInputStream("D:/workspace/idea/demo/src/main/java/com/example/demo/oossdk/322019.jpg");
        PutObjectResult putObject = ossClient.putObject(bucketName, filedir + fileName, inputStream);
        // 设置URL过期时间为10年  3600l* 1000*24*365*10


        String fileUrl = putObject.getETag();
        String[] split = fileUrl.split("/");
        System.out.println(Arrays.toString(split));

        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        System.out.println(ossClient.generatePresignedUrl(bucketName, filedir + split[split.length - 1], expiration).toString());
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 上传回调
     */
    @Test
    public void demo6() throws IOException {
        String bucketName = "sherlock002";
        String objectName = "322019.jpg";
        // 您的回调服务器地址，如http://oss-demo.aliyuncs.com:23450或http://127.0.0.1:9090。
        String callbackUrl = "<yourCallbackServerUrl>";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String content = "Hello OSS";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName,new ByteArrayInputStream(content.getBytes()));
        // 上传回调参数。
        Callback callback = new Callback();
        callback.setCallbackUrl(callbackUrl);
        // 设置回调请求消息头中Host的值，如oss-cn-hangzhou.aliyuncs.com。
        callback.setCallbackHost("oss-cn-hangzhou.aliyuncs.com");
        // 设置发起回调时请求body的值。
        callback.setCallbackBody("{\\\"mimeType\\\":${mimeType},\\\"size\\\":${size}}");
        // 设置发起回调请求的Content-Type。
        //callback.setCalbackBodyType(CallbackBodyType.JSON);
        // 设置发起回调请求的自定义参数，由Key和Value组成，Key必须以x:开始。
        callback.addCallbackVar("x:var1", "value1");
        callback.addCallbackVar("x:var2", "value2");
        putObjectRequest.setCallback(callback);
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        // 读取上传回调返回的消息内容。
        byte[] buffer = new byte[1024];
        putObjectResult.getCallbackResponseBody().read(buffer);
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        putObjectResult.getCallbackResponseBody().close();
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
