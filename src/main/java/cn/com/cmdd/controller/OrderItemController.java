package cn.com.cmdd.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.dao.OrderItemDao;
import cn.com.cmdd.domain.OrderItem;
import cn.com.cmdd.domain.ProductCount;
import cn.com.cmdd.service.ExportExcelService;
import cn.com.cmdd.service.OrderItemService;
import cn.com.cmdd.util.ResponseObject;

@Controller
public class OrderItemController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(OrderItemController.class);
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ExportExcelService exportExcelService;
	
	@Autowired
	private OrderItemDao orderItemDao;


	@RequestMapping(value="/order_item/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody OrderItem orderItem

	){
		/*
		if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			orderItemDao.insertById(orderItem);

			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("orderItem_id", orderItem.getId().toString());
			responseObject.msg = resultMap;

		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();

		}
		return responseObject;
	}

	@RequestMapping(value="/order_item/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg= orderItemDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}
	@RequestMapping(value="/shop/{id}/productCount",method= RequestMethod.GET)
	@ResponseBody
	public ResponseObject getPorduct(
										HttpServletRequest request,
										HttpServletResponse response,
										@PathVariable("id") Integer shop_id,
										Long category_id,
										Long product_id,
										String type,
										@DateTimeFormat(pattern="yyyy-MM-dd")Date start_time,
										@DateTimeFormat(pattern="yyyy-MM-dd")Date end_time )
	{
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			List<ProductCount> productCountList = orderItemService.getOrderItemCount(null,shop_id, category_id, product_id, start_time, end_time);
	
			if(type.equals("query")){
				responseObject.msg = productCountList;
			}else if(type.equals("export_excel")){
				exportExcelService.downLoadExcel(null, null, null,null,productCountList, request, response,null);
			}
			
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/order-product",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject saveOrder_product(
												HttpServletRequest request,
												HttpServletResponse response,
												@RequestBody OrderItem orderItem
												
												){
		/*
		if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
	
			Long id =orderItemService.saveAdditionOrderItem(orderItem);
			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			resultMap.put("orderItem_id",id.toString());
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();

		}
		return responseObject;
	}				
		
	/***\(2\) 修改订单中的菜品信息**
	PUT -H { X-Auth-Token: "xxxxxx" } /order-product/:id -d */
	
	@RequestMapping(value="/order-product/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateOrder_product(
												HttpServletRequest request,
												HttpServletResponse response,
												@PathVariable("id")Long ordersItem_id,
												@RequestBody OrderItem orderItem)
	{
													
		/* if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}											
		*/											
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			orderItemDao.update(orderItem);
			
			HashMap<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("orderItem_id", ordersItem_id.toString());
			
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/order-product/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteOrder_product(	
												HttpServletRequest request,
												HttpServletResponse response,
												@PathVariable("id")Long id){
		/*
		if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}
		*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			orderItemDao.delete(id);;
			responseObject.msg = "操作成功";
		} catch (Exception e) {
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		
		return responseObject;
	}
	
	@RequestMapping(value="/orderItem/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject get(	
								HttpServletRequest request,
								HttpServletResponse response,
								@PathVariable("id")Long id
	){

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			responseObject.msg = orderItemDao.select(id);
			
		} catch (Exception e) {
			
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
			
		}
		
		return responseObject;
	} 
	
	//FIXME:test
	@RequestMapping(value="/orderItem/test",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject get(	
								HttpServletRequest request,
								HttpServletResponse response
	){

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			responseObject.msg = orderItemDao.selectSUMmoneyPreferentialDiscount(null,null,null);
			
		} catch (Exception e) {
			
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			
		}
		
		return responseObject;
	}

}
