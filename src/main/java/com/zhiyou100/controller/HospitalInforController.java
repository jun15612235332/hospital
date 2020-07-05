package com.zhiyou100.controller;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.Doctor;
import com.zhiyou100.model.HospitalInfor;
import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.model.Section;
import com.zhiyou100.service.DoctorService;
import com.zhiyou100.service.HospitalClearService;
import com.zhiyou100.service.HospitalInforService;
import com.zhiyou100.service.RegistrationService;
import com.zhiyou100.service.SectionService;
import com.zhiyou100.util.ResultObject;

@Controller
@RequestMapping("/hospital")
public class HospitalInforController {

	@Autowired
	private HospitalInforService hospitalInforService;
	@Autowired
	private HospitalClearService hospitalClearService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private RegistrationService registrationService;
	/*
	 * 查询全部 
	 */
	@RequestMapping(value="/findAll")
	public String findAll(@RequestParam Map<String, String> map,@RequestParam(defaultValue="1")int pageNum,Model model,String message) {
		//设置分页信息
		PageHelper.startPage(pageNum, 3);
		//查询全部的挂号信息
		List<HospitalInfor> list=hospitalInforService.findAll(map);
		//获取全部的分页信息
		PageInfo<HospitalInfor> pageInfo=new PageInfo<>(list);
		//查询全部的科室信息
		List<Section> sections=sectionService.findAll();
		//查询全部医生
		List<Doctor> doctors=doctorService.findAll();
		model.addAttribute("hospitalInfors", list);
		model.addAttribute("sections", sections);
		model.addAttribute("doctors", doctors);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("map", map);
		model.addAttribute("message", message);
		return "/hospital/index";
	}
	/*
	 * 根据id获取信息
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String findHospitalInforById(String medical_record,Model model,String forwardView,int pageNum) {
		HospitalInfor hos=hospitalInforService.findHospitalInforById(medical_record);
//		System.out.println(hos);
		model.addAttribute("hos", hos);
		model.addAttribute("pageNum", pageNum);
		return "/hospital/"+forwardView;
	}
	/*
	 * 修改对象信息
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(HospitalInfor hos,Model model,int pageNum) {
		hospitalInforService.update(hos);
		model.addAttribute("message", "修改成功");
		return "forward:/hospital/findAll.do?pageNum"+pageNum;
	}
	/*
	 * 退院
	 */
	@RequestMapping(value="/exit",method=RequestMethod.GET)
	public String exitById(String medical_record,int pageNum) {
		hospitalClearService.updateStatus(medical_record);
		return "forward:/hospital/findAll.do?pageNum"+pageNum;
	}
	/*
	 * 出院
	 */
	@RequestMapping(value="/out",method=RequestMethod.GET)
	public String outById(String medical_record,int pageNum) {
		hospitalClearService.outHospital(medical_record);
		return "forward:/hospital/findAll.do?pageNum"+pageNum;
	}
	/*
	 * 跳转添加住院信息页面
	 */
	@RequestMapping(value="/forwardAdd",method=RequestMethod.GET)
	public String forwardAdd() {
		return "/hospital/add";
	}
	/*
	 * 根据病例号查找个人信息
	 */
	@RequestMapping(value="/findInfo")
	@ResponseBody
	public ResultObject findInfo(String medicalRecord) {
		RegistrationInfor reg=registrationService.findRegistrationById(medicalRecord);
		if(reg.getName()!=null) {
			return new ResultObject("200","该病例存在",reg);
		}
		return new ResultObject("404","该病例不存在",reg);
	}
	/*
	 * 住院
	 */
	@RequestMapping(value="/add")
	public String add(HospitalInfor hos,Model model) {
		hospitalInforService.add(hos);
		hospitalClearService.addStatus(hos.getMedicalRecord());
		model.addAttribute("message", "添加成功");
		return "forward:/hospital/findAll.do";
	}
}
