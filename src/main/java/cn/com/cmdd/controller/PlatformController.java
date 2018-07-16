package cn.com.cmdd.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.constant.KEYS;
import cn.com.cmdd.domain.Password;
import cn.com.cmdd.domain.ShopPay;
import cn.com.cmdd.service.PlatformService;
import cn.com.cmdd.service.ShopPayService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class PlatformController {
	private final static Logger LOGGER = LoggerFactory.getLogger(PlatformController.class);
	
	@Autowired
	private PlatformService platformService;
	
	@Autowired
	private ShopPayService shopPayService;
	
	@RequestMapping(value="/platform/{id}/password",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updatePassword(HttpServletRequest request,
										 HttpServletResponse response,
										 @RequestBody Password passwordForm,
										 @PathVariable("id")int id){
		if(!AuthCheck.UserCheck(request, response, KEYS.PLATFORM)){
			return null;
		}
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			int sid = platformService.updatePlatformPassword(id, passwordForm.getOld_password(), passwordForm.getNew_password());
			map.put("user_id", sid);
			responseObject.msg = map;
		}catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}



	@RequestMapping(value="/platform/agent/pay/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getShop(HttpServletRequest request, HttpServletResponse response,
			
			@DateTimeFormat(pattern="yyyy-MM-dd")Date start_time,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date end_time,
			@RequestParam(value = "agent_id",required=false) Integer agent_id,
			@RequestParam(value = "shop_id",required=false) Integer shop_id
			){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			
			ShopPay shopPayByTime = shopPayService.getShopPayByTime(start_time,end_time,agent_id,shop_id);
			responseObject.msg = shopPayByTime;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
}



















