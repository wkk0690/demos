package com.example.demo.ThreadCommunication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author 0X574B4B
 * @create 2018-08-29 19:37
 * @descriptions <p></p >
 */
public class PipedDemo {

    /**
     * 字符管道输入流和字符管道输出流
     */
    public static void main(String[] args) throws IOException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        //输入输出流建立连接
        writer.connect(reader);

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    writer.write(i + "");
                    System.out.println("我写");
                    Thread.sleep(10);
                }
            } catch (Exception e) {

            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            int msg;
            try {
                while ((msg = reader.read()) != -1) {
                    System.out.println("我读");
                    System.out.println(msg);
                }
            } catch (Exception e) {

            }
        });

        t1.start();
        t2.start();

    }
}
