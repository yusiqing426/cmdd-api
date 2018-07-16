package cn.com.cmdd.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.domain.PrintJob;
import cn.com.cmdd.service.PrintJobService;
import cn.com.cmdd.util.ResponseObject;


/**
 * 
 * @author yusiqing_LBCY
 *
 */
@Controller//@CrossOrigin(origins = "*")
public class PrintJobController{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PrintJobController.class);
	
	@Autowired
	private PrintJobService printJobService;
		
	@RequestMapping(value="/shop/{id}/print_job/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getPrintJobList(
										@PathVariable("id")	Integer shop_id
										){
											
		 /*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/									
		 									
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);		
		try {
			
			responseObject.msg = printJobService.getPrintJobList(shop_id);
						 			
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
		}
		return responseObject;					
	}
		
	@RequestMapping(value="/print_job",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject savePrintJob(@RequestBody PrintJob printJob)
	{
											
		/* if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}									
		*/									
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			printJobService.savePrintJob(printJob);
			
		} catch (Exception e) {
			
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/deletePrintJob/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deletePrintJob(@PathVariable("id")Long id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);		
		try {
			
			printJobService.removePrintJob(id);
			
		} catch (Exception e) {
					
			responseObject.code = 	ResponseObject.serverError;
			responseObject.msg	=	e.getLocalizedMessage();			
		}
		
		return responseObject;
	}
}
	
	
