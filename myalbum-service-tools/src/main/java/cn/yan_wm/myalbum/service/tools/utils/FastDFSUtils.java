package cn.yan_wm.myalbum.service.tools.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: fastDFSDemo
 * @description: FastDFS通用工具类
 * @author: yan_zt
 * @create: 2019-12-27 14:47
 */
public class FastDFSUtils {

    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient storageClient = null;
    private static final String GROUP_NAME = "group1";
    static {
        /**加载配置文件*/
        try {
            ClientGlobal.init("config/fastdfs_client.conf");
        } catch (IOException | MyException e) {
            e.printStackTrace();
            System.out.println("load config file fail");
        }
    }

    /**
     * 初始化连接数据
     */
    private static void init() {
        try {
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("init fail");
        }
    }
    /**
     * 上传文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @return 文件存储信息
     * @author: wrh45
     * @date: 2017年8月5日下午11:10:38
     */
    public static String[] uploadFile(String filePath, String fileName, String ext) {
        return uploadFile(null, filePath, fileName, ext);
    }
    /**
     * 上传文件
     * @param fileBuff 文件字节数组
     * @param fileName 文件名称
     * @return 文件存储信息
     * @author: wrh45
     * @date: 2017年8月5日下午11:10:38
     */
    public static String[] uploadFile(byte[] fileBuff, String fileName,String ext) {
        return uploadFile(fileBuff, null, fileName, ext);
    }
    /**
     * 上传文件
     * @param fileBuff 文件字节数组
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @return 文件存储信息
     * @author: wrh45
     * @date: 2017年8月5日下午10:58:19
     */
    private static String[] uploadFile(byte[] fileBuff, String filePath, String fileName, String fileExtName) {
        try {
            if (fileBuff == null && filePath == null) {
                return new String[0];
            }
            // 初始化数据
            if (storageClient == null) {
                init();
            }
            // 设置图片元数据
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName", fileName+"."+fileExtName);
            metaList[1] = new NameValuePair("fileExtName", fileExtName);
            metaList[2] = new NameValuePair("fileSize", String.valueOf(fileBuff.length));
            // 上传文件
            String[] uploadFile = null;
            if (fileBuff != null && filePath == null) {
                if (fileBuff.length == 0) {
                    return new String[0];
                }
                uploadFile = storageClient.upload_file(fileBuff, fileExtName, metaList);
            } else {
                //路径匹配Windown和Linux
                if ("".equals(filePath) || !(filePath.matches("^[a-zA-Z]:{1}([\u4e00-\u9fa5\\w/_\\\\-]+)$") || filePath.matches("^(/[\u4e00-\u9fa5\\w_-]+)+$"))) {
                    return new String[0];
                }
                uploadFile = storageClient.upload_file(filePath, fileExtName, metaList);
            }
            return uploadFile == null ? new String[0] : uploadFile;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (trackerServer != null) {
                    trackerServer.close();
                    trackerServer = null;
                }
                if (storageServer != null) {
                    storageServer.close();
                    storageServer = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
                   /* 获取文件扩展名称
             String fileExtName = "";
             if (fileName != null && !"".equals(fileName) && fileName.contains(".")) {
             fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
             } else {
             return new String[0];
             }*/
        return new String[0];
    }
    /**
     * 删除服务器文件
     * @param  groupName 组名
     * @param  fileName 文件在服务器中名称
     * @author: Yan_zt
     * @date: 2017年8月6日上午12:15:22
     */
    public static int deleteFile(String groupName, String fileName) {
        try {
            if (storageClient == null) {
                init();
            }
            int code = storageClient.delete_file(groupName, fileName);
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The File Delete Fail");
        }
        return -1;
    }
    /**
     * 获取文件信息
     * @param groupName 组名
     * @param remoteFilename 远程文件名
     * @return
     * @author: wrh45
     * @date: 2017年8月8日上午12:25:26
     */
    public static FileInfo getFileInfo(String groupName, String remoteFilename) {
        try {
            if (storageClient == null) {
                init();
            }
            FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFilename);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Get File Info Fail");
        }
        return null;
    }


}
