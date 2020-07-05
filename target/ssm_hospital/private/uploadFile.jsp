<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传下载操作</title>
</head>
<body>
<form id="uploadForm">  
      <p >上传文件： <input type="file" name="file"/></p>  
      <input type="button" value="上传" onclick="doUpload();" />  
</form>  
<img id="imgid" alt="" src="">
<!-- 保存文件id -->
<input type="hidden" id="hid" value="">
<button id="download" onclick="downloadFun();">下载</button>

<script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery.js"></script>
<script type="text/javascript">
function doUpload() {  
     var formData = new FormData($("#uploadForm")[0]);  
     $.ajax({  
          url: '${pageContext.request.contextPath}/fileupload.do' ,  
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (data) {  
        	  alert(data.data.path);
  			$("#imgid").attr("src",data.data.path);
  			$("#hid").val(data.data.id);
          }  
     });  
}  
function downloadFun () {
	var fid = $("#hid").val();
	var str = "?fid="+fid;
	window.open("http://localhost:8080/ssm_hospital/download.do"+str);
}
</script>
</body>
</html>