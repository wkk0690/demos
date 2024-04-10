package com.example.spring.resource;

import org.junit.Test;
import org.springframework.core.io.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * resource
 * https://github.com/xuchengsheng/spring-reading/blob/master/spring-resources/spring-resource/README.md
 * @author wkk
 * @date 2024-04-08
 */
public class ResourceTest {

    public static void main(String[] args) {


    }

    @Test
    public void demo1() throws IOException {
        String path = "application.yml";
        Resource resource = new ClassPathResource(path);
        try (InputStream is = resource.getInputStream()) {
            // 读取和处理资源内容
            System.out.println(new String(is.readAllBytes()));
        }
    }

    @Test
    public void demo2() throws IOException {
        // 请替换我们自己的目录
        String path = "D:\\urce\\myfile.tidea-work-space-xcs\\spring-reading\\spring-resources\\spring-resoxt";
        Resource resource = new FileSystemResource(path);
        try (InputStream is = resource.getInputStream()) {
            // 读取和处理资源内容
            System.out.println(new String(is.readAllBytes()));
        }
    }

    @Test
    public void demo3() throws IOException {
        Resource resource = new UrlResource("https://dist.apache.org/repos/dist/test/test.txt");
        try (InputStream is = resource.getInputStream()) {
            // 读取和处理资源内容
            System.out.println(new String(is.readAllBytes()));
        }
    }

    @Test
    public void demo4() throws IOException {
        byte[] data = "hello world".getBytes();
        Resource resource = new ByteArrayResource(data);
        try (InputStream is = resource.getInputStream()) {
            // 读取和处理资源内容
            System.out.println(new String(is.readAllBytes()));
        }
    }

    @Test
    public void demo5() throws IOException {
        InputStream isSource = new ByteArrayInputStream("hello world".getBytes());
        Resource resource = new InputStreamResource(isSource);
        try (InputStream is = resource.getInputStream()) {
            // 读取和处理资源内容
            System.out.println(new String(is.readAllBytes()));
        }
    }
}
