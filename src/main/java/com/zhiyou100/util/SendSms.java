package com.zhiyou100.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/

/**
 * 短信验证
 */
public class SendSms {
	public static void main(String[] args) {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FwTCksFeycBK8jJ7iwD", "yO2hA2cnrDXE0uwmWkhC09zNFujxlB");
		IAcsClient client = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", "15612235332");
		request.putQueryParameter("SignName", "小深山医院管理系统");
		request.putQueryParameter("TemplateCode", "SMS_184105191");
		request.putQueryParameter("TemplateParam", "{\"code\":\"888888\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}