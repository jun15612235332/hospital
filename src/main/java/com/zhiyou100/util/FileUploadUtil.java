package com.zhiyou100.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUploadUtil {

	// 接收页面上传的文件
	public String receiveFile(MultipartFile file, HttpServletRequest request,String path){
		// 获取输入流对象-->将文件对象变成输入流对象
		InputStream is=null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 调用自定义工具类(在下面)方法,获得项目的真实路径
		String realPath = this.getRealPath(request, path);
		// 为要上传的文件修改一个随机名, 调用自定义方法(在下面),生成随机文件名
		String fileName = this.doFileName(file.getOriginalFilename());
		// 获取输出流对象-->将文件向外输出
		OutputStream os=null;
		try {
			os = new FileOutputStream(new File(realPath, fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// 拷贝
			IOUtils.copy(is, os);
			// 关流
			is.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * 传入一个文件夹的名字返回其在服务器上的真实路径 如果真实路径不存在，直接创建
	 * 
	 * @return
	 */
	private String getRealPath(HttpServletRequest request, String path) {
		String realPath = request.getServletContext().getRealPath(path);
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return realPath;
	}

	/**
	 * 处理文件的名字，这里只是使用UUID实现
	 * 
	 * @param fileName
	 * @return
	 */
	private String doFileName(String fileName) {
		// 获取后缀
		String extension = FilenameUtils.getExtension(fileName);
		// 获取uuid字符串
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + extension;
	}
	/*
	 * 上传至本地磁盘
	 */
	public String receiveFile2(HttpServletRequest request,String img){
		// 将HttpServletRequest对象强转成多部件请求对象
		MultipartHttpServletRequest mhServletRequest = (MultipartHttpServletRequest)request;
		// 获取mhServletRequest中的所有文件名
		//Iterator<String> fileNames = mhServletRequest.getFileNames();
		// 根据页面name属性中的值获取mhServletRequest中的指定文件
		MultipartFile file = mhServletRequest.getFile(img);
		// 判断该文件是否接收成功
		if (file != null) {
			// 上传的路径
			String path="E:\\eclipse\\ssm_hospital\\src\\main\\webapp\\images\\"+file.getOriginalFilename();
			// 开始上传
			try {
				file.transferTo(new File(path));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return "error";
		}
		return file.getOriginalFilename();
	}

}
