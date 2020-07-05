package com.zhiyou100.controller;



import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.Doctor;
import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.model.Section;
import com.zhiyou100.service.DoctorService;
import com.zhiyou100.service.RegistrationService;
import com.zhiyou100.service.SectionService;
import com.zhiyou100.util.MyLog;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private DoctorService doctorService;
	/*
	 * 查询全部 
	 */
	@RequestMapping(value="/findAll")
//	@MyLog("查询全部挂号信息")
	public String findAll(@RequestParam Map<String, String> map,@RequestParam(defaultValue="1")int pageNum,Model model) {
		//设置分页信息
		PageHelper.startPage(pageNum, 3);
		//查询全部的挂号信息
		List<RegistrationInfor> list=registrationService.findAll(map);
		//获取全部的分页信息
		PageInfo<RegistrationInfor> pageInfo=new PageInfo<>(list);
		//查询全部的科室信息
		List<Section> sections=sectionService.findAll();
		//查询全部医生
		List<Doctor> doctors=doctorService.findAll();
		model.addAttribute("registrationList", list);
		model.addAttribute("sections", sections);
		model.addAttribute("doctors", doctors);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("map", map);
		model.addAttribute("pageNum", pageNum);
		return "/registration/index";
	}
	/*
	 * 根据id获取信息
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String findRegistrationById(String medical_record,Model model,String forwardView,int pageNum) {
		RegistrationInfor reg=registrationService.findRegistrationById(medical_record);
		System.out.println(reg);
		model.addAttribute("reg", reg);
		model.addAttribute("pageNum", pageNum);
		return "/registration/"+forwardView;
	}
	/*
	 * 修改对象信息
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(RegistrationInfor reg,Model model,int pageNum) {
		registrationService.update(reg);
		model.addAttribute("message", "修改成功");
		return "forward:/registration/findAll.do?pageNum"+pageNum;
	}
	/*
	 * 退号
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String deleteByUid(String medical_record,Model model,int pageNum) {
		registrationService.retreat(medical_record);
		return "forward:/registration/findAll.do?pageNum"+pageNum;
	}
	/*
	 * 批量退号
	 */
	@RequestMapping(value="/batch")
	public String deleteByUidArr(String[] ids,Model model,String pageNum) {
		registrationService.deleteByUidArr(ids);
		return "forward:/registration/findAll.do?pageNum="+pageNum;
	}
	
	/*
	 * 挂号
	 */
	@RequestMapping(value="/add")
	public String add(RegistrationInfor reg,Model model) {
//		System.out.println(reg);
		if(reg.getName()==null) {
			return "/registration/add";
		}
		//设置病例号
		reg.setMedicalRecord(new Date().getTime()+"");
		registrationService.add(reg);
		model.addAttribute("message", "添加成功");
		return "forward:/registration/findAll.do";
	}
	
	
}
