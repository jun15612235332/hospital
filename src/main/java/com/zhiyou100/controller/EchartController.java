package com.zhiyou100.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.util.ResultObject;

@Controller
@RequestMapping("/echart")
public class EchartController {

	@RequestMapping(value="/month",method=RequestMethod.GET)
	@ResponseBody
	public ResultObject showMonth() {
		double[] month= {100.0,200.0,300.0,400.0,350.0,250.0,150.0};
		
		
		return new ResultObject("200","查询月报表成功",month);
	}
	
	
	@RequestMapping(value="/year",method=RequestMethod.GET)
	@ResponseBody
	public ResultObject two() {
		
		double[] year= {220.0, 182.0, 191.0, 234.0, 290.0, 330.0, 310.0, 123.0, 442.0, 321.0, 90.0, 149.0};
		
		return new ResultObject("200","查询年报表成功",year);
	}
	
}
