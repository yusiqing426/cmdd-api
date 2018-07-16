package cn.com.cmdd.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.easy.excel.ExcelContext;
import org.easy.excel.util.ExcelDownLoadUtil;

import org.springframework.stereotype.Service;

import cn.com.cmdd.domain.Orders;
import cn.com.cmdd.domain.OrdersCountModel;
import cn.com.cmdd.domain.ProductCount;
import cn.com.cmdd.util.DateUtil;


/**
 * 
 * @author ysq19930526
 *
 */

@Service
public class ExportExcelService {
/*	*//** 文件后缀 *//*
	public static final String FILE_SUFFIX = ".xlsx";

	*//** 文件编码 *//*
	public static final String UTF8 = "UTF-8";
	*//** 用户浏览器关键字：IE */		
	public void downLoadExcel(String excelName, String emptyMessage, List<Orders> ordersList, HashMap<String, Object> resultMap, List<ProductCount> productCountList, HttpServletRequest request, HttpServletResponse response,OrdersCountModel ordersCountModels)throws Exception{
		
		String nowDate = DateUtil.getYyyy_MM_dd_HH_mm_ss(new Date());
		
		emptyMessage = "emptyMessage";
		
		if(ordersList!=null){
			//创建excel上下文实例,它的构成需要配置文件的路径			
			ExcelContext context = new ExcelContext("excel-config.xml");		
			//获取POI创建结果
			Workbook workbook = context.createExcel("OrdersModel",ordersList);
			workbook.setSheetName(0, "订单统计");
			Sheet sheetAt0 = workbook.getSheetAt(0);
			int rowCount = ordersList.size()+2;
			Row createRow1 = sheetAt0.createRow(rowCount);
			
			createRow1.createCell(0).setCellValue("营业收入:");			
			rowCount+=1;
			Row createRow2 = sheetAt0.createRow(rowCount);
			createRow2.createCell(1).setCellValue("收入总计:");
			createRow2.createCell(2).setCellValue(ordersCountModels.getSumReceived()+"");
			
			rowCount+=1;
			Row createRow3 = sheetAt0.createRow(rowCount);
			createRow3.createCell(0).setCellValue("收入明细:");
			rowCount+=1;
			Row createRow4 = sheetAt0.createRow(rowCount);
			createRow4.createCell(1).setCellValue("现金收入:");
			createRow4.createCell(2).setCellValue("银行卡收入:");
			createRow4.createCell(3).setCellValue("微信收入:");
			createRow4.createCell(4).setCellValue("支付宝收入:");
			createRow4.createCell(5).setCellValue("会员卡收入:");
			
			rowCount+=1;
			Row createRow5 = sheetAt0.createRow(rowCount);
			createRow5.createCell(1).setCellValue(ordersCountModels.getSumTradeCash()+"");
			createRow5.createCell(2).setCellValue(ordersCountModels.getSumTradeCreditCard()+"");
			createRow5.createCell(3).setCellValue(ordersCountModels.getSumTradeWechat()+"");
			createRow5.createCell(4).setCellValue(ordersCountModels.getSumTradeAlipay()+"");
			createRow5.createCell(5).setCellValue(ordersCountModels.getSumMemberRecharge());
			
			rowCount+=1;
			Row createRow6 = sheetAt0.createRow(rowCount);
			createRow6.createCell(0).setCellValue("支出明细:");
			rowCount+=1;
			Row createRow7 = sheetAt0.createRow(rowCount);
			createRow7.createCell(1).setCellValue("免单:");
			createRow7.createCell(2).setCellValue("会员卡消费:");
			rowCount+=1;
			Row createRow8 = sheetAt0.createRow(rowCount);
			createRow8.createCell(1).setCellValue(ordersCountModels.getSumFreeOrder()+"");
			createRow8.createCell(2).setCellValue(ordersCountModels.getSumMemberConsume()+"");
			
			ExcelDownLoadUtil.downLoadExcel(workbook, nowDate+"-订单统计", emptyMessage, request, response);			
		}else if(productCountList!=null){
			//创建excel上下文实例,它的构成需要配置文件的路径
			ExcelContext context = new ExcelContext("excel-config.xml");
			//获取POI创建结果					
			Workbook workbook = context.createExcel("ProductCountModel",productCountList);
			ExcelDownLoadUtil.downLoadExcel(workbook, nowDate+"-菜品统计", emptyMessage, request, response);	
		}
	}	
}
