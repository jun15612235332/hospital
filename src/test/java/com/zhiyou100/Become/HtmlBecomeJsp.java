package com.zhiyou100.Become;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;;

/**
 * @author YQL
 * @see 把html文件复制一份jsp文件
 *
 */
public class HtmlBecomeJsp {
	public static void main(String[] args) throws IOException {
		// 写自己文件的路径
		File path = new File("D:\\java\\新建文件夹\\java\\项目文档\\ssm项目\\3-静态页面");
		foreach(path);
		System.out.println("结束了");
//		FileReader fr = new FileReader(pa?th);
	}

	// 递归所有文件
	public static void foreach(File path) {
		System.out.println(path.getPath());
		for (String file : path.list()) {
			File childFild = new File(path, file);
			System.out.println(path.getPath());
			if (childFild.isDirectory()) {
				// 此处进行递归
				foreach(childFild);
			} else {
				String childPath = childFild.getPath();
				if (childPath.endsWith(".html")) {
					// 以.html 结尾的文件进行转换
					htmlBecomeJsp(childPath);
					childFild.deleteOnExit();
				}
				System.out.println(childPath);
			}
		}
	}

	public static void htmlBecomeJsp(String childPath) {
		try {
			String headText = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><%@taglib uri=\"http://java.sun.com/jsp/jstl/core\"  prefix=\"c\"%>";
			BufferedReader fr = new BufferedReader(new FileReader(childPath));
			String path = childPath.substring(0, childPath.lastIndexOf(".") + 1) + "jsp";
			BufferedWriter fw = new BufferedWriter(new FileWriter(path));
			fw.write(headText);
			String line = null;
			while ((line = fr.readLine()) != null) {
				fw.write(line);
			}
			System.out.println("日志  ==>  复制成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
