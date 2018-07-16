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

import cn.com.cmdd.domain.ShopPay;
import cn.com.cmdd.service.ShopPayService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class ShopPayController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ShopPayController.class);

	@Autowired
	private ShopPayService shopPayService;

	@RequestMapping(value="/shop/pay",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addShopPay(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopPay shop_pay){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			Integer id = shopPayService.addShopPay(shop_pay);
			
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("shop_pay_id", id.toString());
			responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	
	@RequestMapping(value="agent/{id}/shop/pay/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getShopPaysByAgentId(@PathVariable("id")int agent_id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = shopPayService.getShopPayListByAgentId(agent_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{id}/pay/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getShopPaysByShopId(@PathVariable("id")Integer shop_id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = shopPayService.getShopPayListByShopId(shop_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{id}/pay",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getShopPay(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")int id){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = shopPayService.getShopPayById(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/shop/{id}/pay",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateShopPay(HttpServletRequest request,
										HttpServletResponse response,
										@PathVariable("id") Integer id,
										@RequestBody ShopPay shop_pay){
		/*if(!AuthCheck.UserCheck(request, response,KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			shop_pay.setId(id);
			shopPayService.updateShopPay(shop_pay);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("shop_pay_id", id.toString());
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
}
