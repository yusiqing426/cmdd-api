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

import cn.com.cmdd.dao.LotteryDao;
import cn.com.cmdd.domain.Lottery;
import cn.com.cmdd.service.LotteryService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class LotteryController {
	private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

	@Autowired
	private LotteryService lotteryService;
	
	@Autowired
	private LotteryDao lotteryDao;


	@RequestMapping(value="/lottery/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=lotteryDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/lottery",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addLottery(HttpServletRequest request,
									 HttpServletResponse response,
									 @RequestBody Lottery lottery)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{

			lotteryService.addLottery(lottery);
			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			resultMap.put("lottery_id", lottery.getId().toString());
			responseObject.msg = resultMap;

		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/lottery/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(HttpServletRequest request,
									 HttpServletResponse response,
									 @RequestBody Lottery lottery)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{

			lotteryDao.insertById(lottery);

		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	
	
	
	@RequestMapping(value="/shop/{id}/lottery/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getLotterys(@PathVariable("id")Integer shopId){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = lotteryService.getLotteryListByShop_id(shopId);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/lottery/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getLottery(HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("id")Long id){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = lotteryService.getLotteryById(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/lottery/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateLottery(HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("id")Long id, 
			@RequestBody Lottery lottery)
	{
		/*if(!AuthCheck.UserCheck(request, response,KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			lottery.setId(id);
			lotteryService.updateLottery(lottery);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("lottery_id", id);
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	
}
