package com.zhiyou100.util;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.model.Image;

public class FastDFSUtil {

	public static Image upload(MultipartFile file) throws Exception{
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 获得文件后缀
		String[] split = fileName.split("\\.");
		String suffix = split[1];
		System.out.println("FastDFSUtil : 文件名 : "+fileName+" , 后缀 : "+suffix);
		// 1. 加载配置文件
		ClientGlobal.init("E:/eclipse/ssm_hospital/src/main/resources/fastdfs-client.properties");
		// 2. 创建管理端对象
		TrackerClient trackerClient = new TrackerClient();
		// 3. 通过管理端对象获取连接
		TrackerServer connection = trackerClient.getConnection();
		// 4. 创建存储端对象
		StorageClient1 storageClient = new StorageClient1(connection, null);

		NameValuePair[] meta_list = new NameValuePair[1];
		// 需要传入文件名
		meta_list[0] = new NameValuePair("fileName", fileName);

		// 5. 上传文件
		/*
		 * 参数1:要上传的文件的字节数组
		 * 参数2:要上传的文件类型
		 * 参数3:文件属性信息对象数组
		 * 返回值 : 存储在Storage中的地址
		 */
		String path = storageClient.upload_file1(file.getBytes(), suffix, meta_list);
		System.out.println("FastDFSUtil - path : http://192.168.106.128/"+ path);
		String url="http://192.168.106.128/"+path;
		Image image = new Image();
		image.setId(path);
		image.setPath(url);
		return image;
	}

	public static byte[] download(String fid) throws IOException, MyException {
		System.out.println("接收到的文件 id" + fid);
		// 1. 加载配置文件
		ClientGlobal.init("E:/eclipse/ssm_hospital/src/main/resources/fastdfs-client.properties");
		// 2. 创建管理端对象
		TrackerClient trackerClient = new TrackerClient();
		// 3. 通过管理端对象获取连接
		TrackerServer connection = trackerClient.getConnection();
		// 4. 创建存储端对象
		StorageClient1 storageClient = new StorageClient1(connection, null);
		
		// 5.通过文件id得到文件的字节数组
		byte[] bytes = storageClient.download_file1(fid);
		return bytes;
		
	}
}
