package com.example.demo.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Properties;

/**
 * @author wkk
 * @create 2020/07/09
 * @desc
 * https://github.com/happyfish100/fastdfs/wiki 安装文档
 * https://github.com/happyfish100/fastdfs-client-java java文档
 *
 * mvn install:install-file -DgroupId=org.csource -DartifactId=fastdfs-client-java -Dversion=1.29-SNAPSHOT -Dpackaging=jar -Dfile=D:\workspace\idea\fastdfs-client-java\target\fastdfs-client-java-1.29-SNAPSHOT.jar
 */
@RestController
@RequestMapping("/fastdfs")
public class FastDfsUtils {

    private static StorageClient storageClient;

    static {
        try {
            /*Properties props = new Properties();
            props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, "122.51.32.173:22122");
            ClientGlobal.initByProperties(props);
            trackerClient = new TrackerClient();*/

            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            storageClient = new StorageClient(trackerServer, null);
        } catch (Exception var1) {
            var1.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());

        String[] parts = uploadFile("F:\\图片\\198634.jpg");
        downloadFile("C:\\\\Users\\\\Lenovo\\\\Desktop\\\\aa.jpg", parts[0], parts[1]);
    }

    /**
     * 向FastDFS上传文件
     * @param file
     * @return
     */
    public static String[] upload(MultipartFile file) throws IOException, MyException {
        String oldName = file.getOriginalFilename();
        String[] parts = storageClient.upload_file(file.getBytes(), oldName.substring(oldName.lastIndexOf(".") + 1), null);
        return parts;
    }

    /**
     * 向FastDFS上传文件
     *
     * @param localFilename 本地文件名
     * @return 上传成功，返回组名和该文件在FastDFS中的名称；上传失败，返回null
     */
    public static String[] uploadFile(String localFilename) throws IOException, MyException {
        String[] parts = storageClient.upload_file(localFilename, null, null);
        return parts;
    }

    /**
     * 从FastDFS下载文件
     *
     * @param localFilename  本地文件名
     * @param groupName      文件在FastDFS中的组名
     * @param remoteFilename 文件在FastDFS中的名称
     */
    public static void downloadFile(String localFilename, String groupName, String remoteFilename) throws Exception {
        File file = new File(localFilename);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        byte[] content = storageClient.download_file(groupName, remoteFilename);
        if (content == null || content.length == 0) {
            throw new RuntimeException("文件不存在");
        }
        bos.write(content);
    }

    /**
     * 从FastDFS删除文件
     *
     * @param localFilename  本地文件名
     * @param groupName      文件在FastDFS中的组名
     * @param remoteFilename 文件在FastDFS中的名称
     * @return 0是成功
     */
    public static int deleteFile(String localFilename, String groupName, String remoteFilename) throws IOException, MyException {
        return storageClient.delete_file(groupName, remoteFilename);
    }
}
