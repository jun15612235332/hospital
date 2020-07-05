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

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private SectionService sectionService;
	/*
	 * 查询全部
	 */
	 
	@RequestMapping(value="/findAll")
	public String findAll(@RequestParam Map<String, String> map,@RequestParam(defaultValue="1")int pageNum,Model model) {
		//设置分页信息
		PageHelper.startPage(pageNum, 3);
		//查询全部的挂号信息
		List<Doctor> list=doctorService.findAll(map);
		//获取全部的分页信息
		PageInfo<Doctor> pageInfo=new PageInfo<>(list);
		//查询全部的科室信息
		List<Section> sections=sectionService.findAll();
		//查询全部的医生
		List<Doctor> doctors=doctorService.findAll();
		
		model.addAttribute("doctors", list);
		model.addAttribute("docs", doctors);
		model.addAttribute("sections", sections);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("map", map);
		return "/doctor/index";
	}
	/*
	 *跳转添加添加医生页面
	 */
	@RequestMapping(value="/forwardAdd")
	public String forwardAdd() {
		return "/doctor/add";
	}
	/*
	 * 
	 * 添加医生
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Doctor doctor) {
		doctorService.add(doctor);
		return "forward:/doctor/findAll.do";
	}
	/*
	 * 根据id获取信息
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String findDoctorById(int id,Model model,String forwardView,int pageNum) {
		Doctor doctor=doctorService.findDoctorById(id);
		model.addAttribute("doctor", doctor);
		model.addAttribute("pageNum", pageNum);
		return "/doctor/"+forwardView;
	}
	/*
	 * 修改对象信息
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Doctor doctor,Model model,int pageNum) {
		doctorService.update(doctor);
		model.addAttribute("message", "修改成功");
		return "forward:/doctor/findAll.do?pageNum"+pageNum;
	}
	/*
	 * 根据id删除信息
	 */
//	@RequestMapping(value="/user/delete",method=RequestMethod.GET)
//	public String deleteByUid(int uid,Model model) {
//		int hang=userService.deleteByUid(uid);
//		model.addAttribute("message", "删除成功");
//		return "forward:/user/main.do";
//	}
	/*
	 * 挂号
	 */
	/*@RequestMapping(value="/add")
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
	}*/
}
