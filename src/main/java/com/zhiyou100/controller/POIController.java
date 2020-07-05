package com.zhiyou100.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.service.RegistrationService;

@Controller
@RequestMapping("/poi")
public class POIController {

	@Autowired
	private RegistrationService service;

	/**
	 * 导出Excel 在挂号信息页面,下方有导出. 需求 1) 直接导出全部挂号信息 --> 查全部 2) 导出选中的患者的信息 -->
	 * 向后台发送选中的id即可
	 * 
	 */
	@RequestMapping("/export")
	public void exportExcel(HttpServletResponse resp) throws IOException {
		// 调用业务层,查出全部信息
		List<RegistrationInfor> regs = service.findAll();
		// 1创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2创建Sheet页面
		HSSFSheet sheet = wb.createSheet("挂号信息");
		// 3创建第一行,表头行
		HSSFRow r1 = sheet.createRow(0);
		// 4.创建所需列
		HSSFCell r1c1 = r1.createCell(0);
		HSSFCell r1c2 = r1.createCell(1);
		HSSFCell r1c3 = r1.createCell(2);
		HSSFCell r1c4 = r1.createCell(3);
		HSSFCell r1c5 = r1.createCell(4);
		HSSFCell r1c6 = r1.createCell(5);
		HSSFCell r1c7 = r1.createCell(6);
		HSSFCell r1c8 = r1.createCell(7);
		HSSFCell r1c9 = r1.createCell(8);
		HSSFCell r1c10 = r1.createCell(9);
		HSSFCell r1c11 = r1.createCell(10);
		HSSFCell r1c12 = r1.createCell(11);
		HSSFCell r1c13 = r1.createCell(12);
		HSSFCell r1c14 = r1.createCell(13);
		HSSFCell r1c15 = r1.createCell(14);
		HSSFCell r1c16 = r1.createCell(15);
		HSSFCell r1c17 = r1.createCell(16);
		r1c1.setCellValue("病例号");
		r1c2.setCellValue("姓名");
		r1c3.setCellValue("证件类型");
		r1c4.setCellValue("证件号");
		r1c5.setCellValue("社保号");
		r1c6.setCellValue("联系电话");
		r1c7.setCellValue("是否自费");
		r1c8.setCellValue("性别");
		r1c9.setCellValue("职业");
		r1c10.setCellValue("初复诊");
		r1c11.setCellValue("挂号医生");
		r1c12.setCellValue("状态");
		r1c13.setCellValue("备注");
		r1c14.setCellValue("挂号时间");
		r1c15.setCellValue("所挂科室");
		r1c16.setCellValue("年龄");
		r1c17.setCellValue("挂号费");
		// 遍历集合,确定集合有几个元素,创建几行
		for (int i = 0; i < regs.size(); i++) {
			// 新创建一行
			HSSFRow row = sheet.createRow(i + 1);
			// 在行内创建四列
			HSSFCell c1 = row.createCell(0);
			HSSFCell c2 = row.createCell(1);
			HSSFCell c3 = row.createCell(2);
			HSSFCell c4 = row.createCell(3);
			HSSFCell c5 = row.createCell(4);
			HSSFCell c6 = row.createCell(5);
			HSSFCell c7 = row.createCell(6);
			HSSFCell c8 = row.createCell(7);
			HSSFCell c9 = row.createCell(8);
			HSSFCell c10 = row.createCell(9);
			HSSFCell c11 = row.createCell(10);
			HSSFCell c12 = row.createCell(11);
			HSSFCell c13 = row.createCell(12);
			HSSFCell c14 = row.createCell(13);
			HSSFCell c15 = row.createCell(14);
			HSSFCell c16 = row.createCell(15);
			HSSFCell c17 = row.createCell(16);
			c1.setCellValue(regs.get(i).getMedicalRecord());
			c2.setCellValue(regs.get(i).getName());
			c3.setCellValue(regs.get(i).getCertificateType());
			c4.setCellValue(regs.get(i).getIdNum());
			c5.setCellValue(regs.get(i).getSocialSafeNum());
			c6.setCellValue(regs.get(i).getPhone());
			c7.setCellValue(regs.get(i).getSelfPaying());
			c8.setCellValue(regs.get(i).getSex());
			c9.setCellValue(regs.get(i).getCareer());
			c10.setCellValue(regs.get(i).getEarlyAppointment());
			c11.setCellValue(regs.get(i).getDoctor().getName());
			c12.setCellValue(regs.get(i).getStatus());
			c13.setCellValue(regs.get(i).getNoted());
			Date time = regs.get(i).getTime();
			String format = new SimpleDateFormat("yyyy-MM-dd").format(time);
			c14.setCellValue(format);
			c15.setCellValue(regs.get(i).getSection().getSeccoName());
			c16.setCellValue(regs.get(i).getAge());
			c17.setCellValue(regs.get(i).getRegistrationFee());
		}
		// 解决响应中文文件名乱码问题
		String filename = URLEncoder.encode("挂号信息表", "utf-8");
		// 浏览器响应下载弹框
		resp.setHeader("Content-disposition", "attachment;filename=" + filename + ".xls");
		resp.setContentType("application/msexcel");
		// 输出
		OutputStream out = resp.getOutputStream();
		wb.write(out);
		out.close();
	}
	
	/**
	 * 导入Excel, 可以导入.xls .xlsx
	 */
	@RequestMapping("/import")
	@ResponseBody
	public static List<ArrayList<Object>> getExcelData(MultipartFile file) throws IOException {
		// 检查文件是.xls 或者是.xlsx
		checkFile(file);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<ArrayList<Object>> list = new ArrayList<>();
		if (workbook != null) {
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
				// 获得当前sheet工作表
				Sheet sheet = workbook.getSheetAt(sheetNum);
				if (sheet == null) {
					continue;
				}
				// 获得当前sheet的开始第一行
				int firstRowNum = sheet.getFirstRowNum();
				// 获得当前sheet的结束行
				int lastRowNum = sheet.getLastRowNum();
				// 循环除了所有行,如果要循环除第一行以外的就firstRowNum+1
				for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
					// 获得当前行
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					// 获得当前行的开始列
					int firstCellNum = row.getFirstCellNum();
					// 获得当前行的最后一列,即列数
					int lastCellNum = row.getLastCellNum();
					if (lastCellNum > 0) {
						// 创建集合,存储单元格内容
						ArrayList<Object> cellValues = new ArrayList<>();
						// 循环当前行
						for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
							// 获得一个单元格的内容
							Cell cell = row.getCell(cellNum);
							// 将单元格内容添加进集合
							cellValues.add(getCellValue(cell));
						}
						System.out.println("单元格中的内容 : " + cellValues);
						// 将一行内容添加进集合
						list.add(cellValues);
					}
				}
			}
		}
		System.out.println("Excel中的内容 : " + list);
		return list;
	}

	/**
	 * 检查文件
	 *
	 * @param file
	 * @throws IOException
	 */
	public static void checkFile(MultipartFile file) throws IOException {
		// 判断文件是否存在
		if (null == file) {
			System.err.println("文件不存在！");
		}
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 判断文件是否是excel文件
		if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
			System.err.println("不是excel文件");
		}
	}

	public static Workbook getWorkBook(MultipartFile file) {
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = file.getInputStream();
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007 及2007以上
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			e.getMessage();
		}
		return workbook;
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 判断数据的类型
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = stringDateProcess(cell);
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}

	public static String stringDateProcess(Cell cell) {
		String result = new String();
		if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
			SimpleDateFormat sdf = null;
			if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
				sdf = new SimpleDateFormat("HH:mm");
			} else {// 日期
				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			}
			Date date = cell.getDateCellValue();
			result = sdf.format(date);
		} else if (cell.getCellStyle().getDataFormat() == 58) {
			// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			double value = cell.getNumericCellValue();
			Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
			result = sdf.format(date);
		} else {
			double value = cell.getNumericCellValue();
			CellStyle style = cell.getCellStyle();
			DecimalFormat format = new DecimalFormat();
			String temp = style.getDataFormatString();
			// 单元格设置成常规
			if (temp.equals("General")) {
				format.applyPattern("#");
			}
			result = format.format(value);
		}

		return result;
	}

	public static InputStream convertorStream(Workbook workbook) {
		InputStream in = null;
		try {
			// 临时缓冲区
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// 创建临时文件
			workbook.write(out);
			byte[] bookByteAry = out.toByteArray();
			in = new ByteArrayInputStream(bookByteAry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}
}
