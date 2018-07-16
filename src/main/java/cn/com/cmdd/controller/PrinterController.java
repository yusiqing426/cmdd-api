package cn.com.cmdd.controller;



import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.dao.PrinterDao;
import cn.com.cmdd.domain.Printer;
import cn.com.cmdd.service.PrinterService;
import cn.com.cmdd.util.ResponseObject;


/**
 * @typeName PrinterController
 * @description 
		Summary : TODO 
		Member Property :TODO
		Member Method:TODO
 * @author yusiqing
 * @date 2017年6月16日 下午10:36:33
 */
@Controller//@CrossOrigin(origins = "*")
public class PrinterController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PrinterController.class);
	
	@Autowired
	private PrinterService printerService;
	
	@Autowired
	private PrinterDao printerDao;
		
	@RequestMapping(value="/printer",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject savePrinter(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Printer printer
	)
	{
											
		/*
		 if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/										
											
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			printerService.savePrinter(printer);
			
		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}

	@RequestMapping(value="/printer/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Printer printer
	)
	{
		/*
		 if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			printerDao.insertById(printer);

			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			resultMap.put("printer_id", printer.getId().toString());
			responseObject.msg = resultMap;

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{id}/printer/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getPrinter(
										HttpServletRequest request,
										HttpServletResponse response,
										@PathVariable("id")Integer shop_id
										
										){
											
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}	*/										
											
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			responseObject.msg = printerService.getPrinterListByShop(shop_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/printer/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updatePrinter(
											HttpServletRequest request,
											HttpServletResponse response,
											@PathVariable("id")Long id,
											@RequestBody Printer Printer
											
											){
												
		/*
		if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}	
		*/											
											
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			printerService.updatePrinter(Printer);
			HashMap<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("printer_id", id.toString());
			responseObject.msg=resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/printer/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getPrinterById(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id)
	{
											
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/											
															
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {					
			responseObject.msg = printerService.getPrinterById(id);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/printer/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deletePrinter(
											HttpServletRequest request,
											HttpServletResponse response,
											@PathVariable("id")Long id
											
											){
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}	*/	
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			printerService.deletePrinter(id);
			HashMap<String, Object> resultmap = new HashMap<String,Object>();
			resultmap.put("printer_id", id);
			responseObject.msg=resultmap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{shop_id}/printer/printer_type/{printer_type}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getPrinterByPrinter_type(
											HttpServletRequest request,
											HttpServletResponse response,
											@PathVariable("printer_type")Long printer_type,
											@PathVariable("shop_id") Integer shop_id
										){
											
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/																						
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {					
			responseObject.msg=printerService.getPrinterByPrinter_type(shop_id,printer_type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/printer/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=printerDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}
}
