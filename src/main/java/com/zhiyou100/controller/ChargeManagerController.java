package com.zhiyou100.controller;



import java.text.SimpleDateFormat;
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
import com.zhiyou100.model.ChargeManager;
import com.zhiyou100.model.Doctor;
import com.zhiyou100.model.PayItems;
import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.model.Section;
import com.zhiyou100.service.ChargeManagerService;
import com.zhiyou100.service.DoctorService;
import com.zhiyou100.service.PayItemsService;
import com.zhiyou100.service.RegistrationService;
import com.zhiyou100.service.SectionService;

@Controller
@RequestMapping("/charge")
public class ChargeManagerController {

	@Autowired
	private ChargeManagerService chargeService;
	@Autowired
	private PayItemsService payItemsService;
	@Autowired
	private RegistrationService registrationService;
	/*
	 * 查询全部 
	 */
	@RequestMapping(value="/findAll")
	public String findAll(@RequestParam Map<String, String> map,@RequestParam(defaultValue="1")int pageNum,Model model) {
		//设置分页信息
		PageHelper.startPage(pageNum, 3);
		//查询全部的挂号信息
		List<ChargeManager> list=chargeService.findAll(map);
		//获取全部的分页信息
		PageInfo<ChargeManager> pageInfo=new PageInfo<>(list);
		model.addAttribute("ChargeManagers", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("map", map);
		return "/hospital/charge";
	}
	/*
	 * 根据病例号查找个人信息
	 */
	@RequestMapping(value="/findInfo")
	public String findInfo(String medicalRecord,Model model) {
		RegistrationInfor reg=registrationService.findRegistrationById(medicalRecord);
		model.addAttribute("reg", reg);
		return "/hospital/charge-new";
	}
	/*
	 * 根据id获取付费信息
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String findChargeById(int id,Model model,int pageNum) {
		ChargeManager ch=chargeService.findChargeById(id);
		//获取所有的收费项目
		List<PayItems> items = payItemsService.findAll();
		model.addAttribute("ch", ch);
		model.addAttribute("items", items);
		model.addAttribute("pageNum", pageNum);
		return "/hospital/charge-edit";
	}
	/*
	 * 修改对象信息
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(ChargeManager ch,Model model,int pageNum) {
		chargeService.update(ch);
		model.addAttribute("message", "修改成功");
		return "forward:/charge/findAll.do?pageNum"+pageNum;
	}
	/*
	 * 删除
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String deleteById(int id,Model model,int pageNum) {
		chargeService.delete(id);
		return "forward:/charge/findAll.do?pageNum"+pageNum;
	}
	
	/*
	 * 跳转到添加收费项目页面
	 */
	@RequestMapping(value="/forwardAdd")
	public String forwardAdd(Model model) {
		return "/hospital/charge-new";
	}
	/*
	 * 添加收费项目
	 */
	@RequestMapping(value="/add")
	public String add(ChargeManager ch,Model model) {
		System.out.println("测试参数 : "+ch);
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		ch.setChargeTime(format);
		System.out.println("测试参数 2: "+ch);
		chargeService.add(ch);
		model.addAttribute("message", "添加成功");
		return "forward:/charge/findAll.do";
	}
}
