package com.zhiyou100.controller;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.Drug;
import com.zhiyou100.service.DrugService;
import com.zhiyou100.util.FileUploadUtil;
import com.zhiyou100.util.ResultObject;

@Controller
@RequestMapping("/medicine")
public class DrugController {

	@Autowired
	private DrugService drugService;
	/*
	 * 查询全部 
	 */
	@RequestMapping(value="/findAll")
	public String findAll(@RequestParam Map<String, String> map,@RequestParam(defaultValue="1")int pageNum,Model model) {
		//设置分页信息
		PageHelper.startPage(pageNum, 3);
		//查询全部的挂号信息
		List<Drug> list=drugService.findAll(map);
		//获取全部的分页信息
		PageInfo<Drug> pageInfo=new PageInfo<>(list);
		model.addAttribute("drugs", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("map", map);
		return "/medicine/index";
	}
	/*
	 * 根据id获取信息
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String findMedicineById(String drugNum,Model model,String forwardView,int pageNum) {
		Drug drug=drugService.findMedicineById(drugNum);
		model.addAttribute("drug", drug);
		model.addAttribute("pageNum", pageNum);
		return "/medicine/"+forwardView;
	}
	/*
	 * 修改对象信息
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Drug drug,Model model,int pageNum,MultipartFile img,HttpServletRequest request) {
		//调用文件上传工具类,实现文件上传
//		FileUploadUtil fileUploadUtil = new FileUploadUtil();
//		String file = fileUploadUtil.receiveFile2(request,"img");
//		drug.setDrugUrl(file);
		drugService.update(drug);
		model.addAttribute("message", "修改成功");
		return "forward:/medicine/findAll.do?pageNum="+pageNum;
	}
	/*
	 *跳转添加新药页面
	 */
	@RequestMapping(value="/forwardAdd")
	public String forwardAdd() {
		return "/medicine/add";
	}
	/*
	 * 添加新药
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Drug drug) {
		drugService.add(drug);
		return "forward:/medicine/findAll.do";
	}
	/*
	 * 文件上传,图片异步回显
	 */
	@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
	@ResponseBody
	public ResultObject fileUpload(MultipartFile drugUrl,HttpServletRequest request) throws IOException{
		// 1 获取服务器路径 - 最终要上传的目的地路径
		String path = request.getServletContext().getRealPath("/images");
//		System.out.println("目的地文件路径 : " + path);

		// 2 判断要上传的文件的路径是否存在 ?
		File file = new File(path);
		// 如果不存在,建立这个目录
		if (!file.exists()) {
			file.mkdirs();
		}

		// 3获取文件的名字
		String fileName = drugUrl.getOriginalFilename();
//		System.out.println("文件名 : " + fileName); // justdoit.jpg

		// 4 确定上传的路径 /tomcat/webapp/springmvc02/images xxx.jpg
		File descFile = new File(file, fileName);
		// 5.文件上传
		FileUtils.writeByteArrayToFile(descFile, drugUrl.getBytes());
//		System.out.println("上传完成");
		String url = "\\images\\" + fileName;
		request.setAttribute("path", url);
		return new ResultObject("200","上传成功",url);
	}
}
