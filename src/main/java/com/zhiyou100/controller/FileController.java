package com.zhiyou100.controller;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.model.Image;
import com.zhiyou100.util.FastDFSUtil;
import com.zhiyou100.util.ResultObject;

@Controller
public class FileController {

	@RequestMapping("fileupload")
	@ResponseBody
	public ResultObject fastDFSUpload(MultipartFile file) throws Exception {
		Image data = FastDFSUtil.upload(file);
		return new ResultObject("200","上传成功",data);
	}

	@RequestMapping("/download.do")
	public void download(HttpServletResponse response, String fid) throws Exception {
		// 获得字节数组
		byte[] bytes = FastDFSUtil.download(fid);
		// 解决响应中文文件名乱码问题
		String filename = URLEncoder.encode("aa", "utf-8");
		// 浏览器响应下载弹框
		response.setHeader("Content-disposition", "attachment;filename=" + filename + ".png");
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes); // 输出数据
		outputStream.flush();
		outputStream.close();

	}
}
