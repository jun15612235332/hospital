<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value='/Css/bootstrap.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/Css/bootstrap-responsive.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/Css/style.css'/>" />
    <script type="text/javascript" src="<c:url value='/Js/jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Js/jquery.sorted.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Js/bootstrap.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Js/ckform.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Js/common.js'/>"></script>

 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
     $(function () {
		$('#newNav').click(function(){
				window.location.href="<c:url value='/user/forwardAdd.do'/>";
		 });
    });
	
	
     function checkall(){
			var alls=document.getElementsByName("check");
			var ch=document.getElementById("checkall");
			if(ch.checked){
				for(var i=0;i<alls.length;i++){
					alls[i].checked=true;	
				}	
			}else{
				for(var i=0;i<alls.length;i++){
					alls[i].checked=false;	
				}	
			}
		}
		function delAll(){
			var alls=document.getElementsByName("check");
			var pageNum=document.getElementById("hid").value;
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认操作?")){
					window.location.href="/ssm_hospital/user/batch.do?ids="+ids+"&pageNum="+pageNum;
				}
			}else{
				alert("请选中要操作的项");
			}
		}
    </script>
</head>
<body>
<form id="form" class="form-inline definewidth m20" action="<c:url value='/user/findAll.do'/>" method="get">
	<input type="hidden" name="pageNum" value="${pageNum }" id="hid">    
	用户名称：
    <input type="text" name="usernameMap" id="username" class="abc input-default" value="${map.usernameMap }">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>
    <button type="reset" class="btn btn-primary" type="button">清空</button> 
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
    	<th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>用户账户</th>
        <th>真实姓名</th>
        <th>角色</th>
        <th>状态</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
    	<tr>
    		<th><input type="checkbox" name="check" value="${user.userName }"></th>
    		<td style="vertical-align:middle;">${user.userName }</td>
    		<td style="vertical-align:middle;">${user.realName }</td>
    		<td style="vertical-align:middle;">${user.role.roleName }</td>
    		<td style="vertical-align:middle;">${user.status==1?'正常':'禁用' }</td>
    		<td style="vertical-align:middle;">
    			<a href="<c:url value='/user/detail.do?userName=${user.userName }&pageNum=${pageInfo.pageNum }'/>">编辑</a>&nbsp;&nbsp;&nbsp;
    			<a href="<c:url value='/user/delete.do?userName=${user.userName }&pageNum=${pageInfo.pageNum }'/>">删除</a>
    		</td>
    	</tr>
    </c:forEach>
</table>
 <table class="table table-bordered table-hover definewidth m10" >
  	<tr>
  		<th colspan="5">  
 			<div class="inline pull-right page">
	          <a onclick="toKeywordFind(${pageInfo.firstPage })" href="#" >首页</a> 
	          <a onclick="toKeywordFind(${pageInfo.prePage })" href="#">上一页</a>     
	          <c:forEach begin="1" end="${pageInfo.pages }" step="1" var="pageNum">
		          <a onclick="toKeywordFind(${pageInfo.pageNum })" href="#" class="${pageNum==pageInfo.pageNum?'current':'' }">${pageNum }</a>
	          </c:forEach>
			  <a onclick="toKeywordFind(${pageInfo.nextPage })" href="#">下一页</a>
			  <a onclick="toKeywordFind(${pageInfo.lastPage })" href="#">尾页</a>
			  &nbsp;&nbsp;&nbsp;
			     共<span class='current'>${pageInfo.total }</span>条记录<span class='current'> ${pageInfo.pageNum }/${pageInfo.pages } </span>页
	 		</div>
	 		<div>
				 <button type="button" class="btn btn-success" id="newNav">添加用户</button>&nbsp;&nbsp;&nbsp;
				 <button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button>
			</div>
	 	</th>
	 </tr>
  </table>
                                                                                            
</body>
<script type="text/javascript">
	//模糊查询并分页显示
	function toKeywordFind(pageNum){
		//获取隐藏标签中当前页的值
		var hid = document.getElementById("hid");
		hid.value=pageNum;
		//获取模糊查询form标签对象
		var form = document.getElementById("form");
		//让form表单执行
		form.submit();
		//停止当前链接的跳转
		return false;
	}
</script>
</html>