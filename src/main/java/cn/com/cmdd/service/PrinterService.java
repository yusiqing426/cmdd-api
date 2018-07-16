package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.CategoryDao;
import cn.com.cmdd.dao.PrinterDao;
import cn.com.cmdd.domain.Category;
import cn.com.cmdd.domain.Printer;

import java.util.List;

@Service
@Transactional
public class PrinterService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PrinterService.class);
	
	@Autowired
	private PrinterDao printerDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public void savePrinter(Printer printer){

		printerDao.savePrinter(printer);
	
	}
	
	public void deletePrinter(Long id){
		printerDao.deletePrinter(id);
		
	}
	
	public void updatePrinter(Printer printer){


		printerDao.updatePrinter(printer);
	}
	
	public List<Printer> getPrinterListByShop(Integer shop_id){
		List<Printer> printers = printerDao.getPrinter(shop_id,null);
		
		if(printers!=null){
			for (Printer printer : printers) {
				if(printer.getCategory_name() == null){
					List<Category> categorys = categoryDao.getCategory(null,printer.getPrinter_type().longValue());
					Category category = categorys.size()==0?null:categorys.get(0);
					printer.setCategory_name(category.getName());
				}
			}
		}
		
		return printers;
	}
	
	public Printer getPrinterById(Long id){
		List<Printer> printers = printerDao.getPrinter(null,id);
		Printer printer = printers.size()==0?null:printers.get(0);
		if(printer.getCategory_name() == null){
			List<Category> categorys = categoryDao.getCategory(null,printer.getPrinter_type());
			Category category = categorys.size()==0?null:categorys.get(0);
			printer.setCategory_name(category.getName());
		}
		return printer;						
	}
	
	public Printer getPrinterByPrinter_type(Integer shop_id,Long printer_type){
		return printerDao.getPrinterNameByPrinter_type(shop_id,printer_type);
	}
}
