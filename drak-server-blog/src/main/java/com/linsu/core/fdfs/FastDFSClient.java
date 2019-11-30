package com.linsu.core.fdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class FastDFSClient {

    private StorageClient1 storageClient1 = null;

    public FastDFSClient(){}

    private String httpServerUrl=  "";

    public FastDFSClient(FastDFSConfig config) {
        try {
            //初始化配置
            Properties p = new Properties();
            p.setProperty("fastdfs.tracker_servers", config.getTracker_servers());
            p.setProperty("fastdfs.connect_timeout_in_seconds", config.getConnect_timeout_in_seconds());
            p.setProperty("fastdfs.network_timeout_in_seconds", config.getNetwork_timeout_in_seconds());
            p.setProperty("fastdfs.charset", config.getCharset());
            p.setProperty("fastdfs.http_anti_steal_token", config.getHttp_anti_steal_token());
            p.setProperty("fastdfs.http_secret_key", config.getHttp_secret_key());
            p.setProperty("fastdfs.http_tracker_http_port", config.getHttp_tracker_http_port());
            ClientGlobal.initByProperties(p);
            httpServerUrl = config.getHttp_server_url();

            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                throw new IllegalStateException("getConnection return null");
            }

            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                throw new IllegalStateException("getStoreStorage return null");
            }

            storageClient1 = new StorageClient1(trackerServer,storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @return
     */
    public String uploadFile(MultipartFile file) {
        return uploadFile(file,null);
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @param metaList 文件元数据
     * @return
     */
    public String uploadFile(MultipartFile file, Map<String,String> metaList) {
        try {
            byte[] buff = file.getBytes();
            String fielname = file.getOriginalFilename();
            String ext = fielname.substring(fielname.lastIndexOf(".")+1);
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<String,String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name,value);
                }
            }
            String fdfsPath = storageClient1.upload_file1(buff, ext,nameValuePairs);
            return getAccessPath(fdfsPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件元数据
     * @param fileId 文件ID
     * @return
     */
    public Map<String,String> getFileMetadata(String fileId) {
        try {
            NameValuePair[] metaList = storageClient1.get_metadata1(fileId);
            if (metaList != null) {
                HashMap<String,String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(),metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     * @param fileId 文件ID
     * @return 删除失败返回-1，否则返回0
     */
    public int deleteFile(String fileId) {
        try {
            return storageClient1.delete_file1(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 下载文件
     * @param fileId 文件ID（上传文件成功后返回的ID）
     * @return
     */
    public byte[] downloadFile(String fileId) {
        byte[] content = null;
        try {
            content = storageClient1.download_file1(fileId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return content;
    }

    private String getAccessPath(String fdfsPath){
        return httpServerUrl + "/" + fdfsPath;
    }

    public void main(String[] args){
        FastDFSClient client = new FastDFSClient();
        byte[] file = client.downloadFile("group1/M00/00/00/wKgK61uOJBeITD6-AAANNY73abgAAAAFQ0kHfEAAA1Z242.jpg");
        System.out.println(file);
    }


}
