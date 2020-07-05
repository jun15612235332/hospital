<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>年营业额</title>
</head>
<body>


<div id="main" style="width:600px;height:400px;"></div>



<script type="text/javascript" src="/ssm_hospital/Js/echarts.min.js"></script>
<script type="text/javascript" src="/ssm_hospital/Js/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//页面加载完,发出请求,加载数据
	$.ajax({
		url:"/ssm_hospital/echart/year.do",
		type:"get",
		success:function(data){
			console.log(data);
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('main'));

			// 指定图表的配置项和数据
			var option = {
					tooltip: { }, // 图上的悬浮提示
			 	xAxis: {
			        type: 'category',
			        data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月','九月','十月','十一月','十二月']
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [{
			        data: data.data, // 直接将后台数组变成前端数组,直接可用
			        type: 'line'
			    }]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
	})
})

</script>

</body>
</html>