package cn.com.cmdd.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.cmdd.domain.Call_service;
import cn.com.cmdd.service.Call_serviceService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class Call_serviceController {

	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Call_serviceController.class);
		
	@Autowired
	private Call_serviceService callServiceService;
	
	/***\(1\) 获取呼叫服务列表**
	GET /shop/:id/call-service/list*/
	
	@RequestMapping(value="/shop/{id}/call-service/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getCall_service(
											HttpServletRequest request,
											HttpServletResponse response,
											@PathVariable("id")Integer shop_id
											
											){
		
		/* if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			responseObject.msg = callServiceService.getCall_service(shop_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg	= e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	/***\(2\) 增加呼叫服务**

	POST -H { X-Auth-Token: "xxxxxx" } /call-service -d 

	```*/
	
	@RequestMapping(value="/call-service",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject saveCall_service(
												HttpServletRequest request,
												HttpServletResponse response,
												@RequestBody Call_service call_service
												
											){
											
		/* if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			Integer call_service_id = callServiceService.saveCall_service(call_service);

			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			
			responseObject.msg = resultMap.put("call_service_id",call_service_id );
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg  = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	/***\(3\) 删除呼叫服务**

	DELETE -H { X-Auth-Token: "xxxxxx" } /call-service/:id*/
	
	@RequestMapping(value="/call-service/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteCall_service(
												HttpServletRequest request,
												HttpServletResponse response,
												@PathVariable("id")Integer id
												
											){
		
		/* if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			callServiceService.deleteCall_service(id);
			
			responseObject.msg = "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	/***\(4\) 修改呼叫服务**

	PUT -H { X-Auth-Token: "xxxxxx" } /call-service -d 

	```*/
	
	@RequestMapping(value="/call-service/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateCall_service(
												HttpServletRequest request,
												HttpServletResponse response,
												@RequestBody Call_service call_service,
												@PathVariable("id")Integer  id
											){
											
		/* if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		System.out.println(id);
		try {
			HashMap<String, Object> hashMap = new HashMap<String,Object>();
			
			callServiceService.updatePrinter(call_service);

			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			
			resultMap.put("call_service_id",id);
			
			responseObject.msg = resultMap; 
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg  = e.getLocalizedMessage();
		}
		return responseObject;
	}
}
