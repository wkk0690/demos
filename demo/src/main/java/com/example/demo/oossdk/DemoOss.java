package com.example.demo.oossdk;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author 0X574B4B
 * @create 2018-10-09 15:31
 * @desc 测试图片上传
 */
public class DemoOss {

    OSSClientUtil ossClient = new OSSClientUtil();

    @Test
    public void demo1() throws Exception {
        File file = new File("D:/322019.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile mockMultipartFile = new MockMultipartFile(file.getName(), inputStream);

        String name = ossClient.uploadImg2Oss(mockMultipartFile);
        String imgUrl = ossClient.getImgUrl(name);
        System.out.println(imgUrl);
//        String[] split = imgUrl.split("\\?");
//        System.out.println(split[0]);
    }

    @Test
    public void demo2() throws Exception {
        ossClient.uploadImg2Oss("D:/322019.jpg");
        String imgUrl = ossClient.getImgUrl("322019.jpg");
        System.out.println(imgUrl);
//        String[] split = imgUrl.split("\\?");
//        System.out.println(split[0]);
    }
}
