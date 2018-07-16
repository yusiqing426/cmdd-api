package cn.com.cmdd.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.dao.KitchenDao;
import cn.com.cmdd.domain.Kitchen;
import cn.com.cmdd.service.KitchenService;
import cn.com.cmdd.util.ResponseObject;
import org.springframework.web.client.RestTemplate;

@Controller
public class KitchenController {
	private final static Logger LOGGER = LoggerFactory.getLogger(KitchenController.class);

	@Autowired
	private KitchenService kitchenService;
	@Autowired
	private KitchenDao kitchenDao;

	@RequestMapping(value="/kitchen",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addKitchen(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Kitchen kitchen)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			kitchenService.addKitchen(kitchen);
            HashMap<String, Object> resultMap = new HashMap<String,Object>();
            resultMap.put("kitchen_id", kitchen.getId().toString());
            responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/kitchen/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Kitchen kitchen)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{

			kitchenDao.insertById(kitchen);
            HashMap<String, Object> resultMap = new HashMap<String,Object>();
            resultMap.put("kitchen_id", kitchen.getId().toString());
            responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}



	@RequestMapping(value="/kitchen/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getKitchen(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("id")Long id)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = kitchenService.getKitchen(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/shop/{id}/kitchen/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getKitchenListByShop_id(@PathVariable("id")Integer shop_id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = kitchenService.getKitchenList(shop_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/kitchen/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateKitchen(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable("id")Long id, 
			@RequestBody Kitchen kitchen)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			kitchen.setId(id);
			kitchenService.updateKitchen(kitchen);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("kitchen_id", id);
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/kitchen/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteAddress(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("id")Long id)
	{
		/*if(!AuthCheck.UserCheck(request, response,KEYS.SHOP)){
			return null;
		}*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			kitchenService.deleteKitchen(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/kitchen/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=kitchenDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}




}
