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

import cn.com.cmdd.constant.KEYS;
import cn.com.cmdd.domain.AgentPay;
import cn.com.cmdd.service.AgentPayService;
import cn.com.cmdd.util.ResponseObject;


@Controller//@CrossOrigin(origins = "*")
public class AgentPayController {
	private final static Logger LOGGER = LoggerFactory.getLogger(AgentPayController.class);

	@Autowired
	private AgentPayService agentPayService;
	
	//增加代理商缴费信息
	@RequestMapping(value="/agent/pay",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addShopPay(HttpServletRequest request, HttpServletResponse response, @RequestBody AgentPay agentPay){
		if(!AuthCheck.UserCheck(request, response, KEYS.PLATFORM)){
			return null;
		}
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
			try{
				Integer id = agentPayService.addAgentPay(agentPay);
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("agent_pay_id", id);
				responseObject.msg = resultMap;
			}catch(Exception e){
				responseObject.code = ResponseObject.serverError;
				responseObject.msg = e.getLocalizedMessage();
				e.printStackTrace();
			}
		return responseObject;
	}
	
	//获取未缴费代理商列表
	@RequestMapping(value="/agent/pay/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getAgentPays(){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = agentPayService.getAgentPayList();
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	//根据id,得到代理商缴费信息
	@RequestMapping(value="/agent/{id}/pay",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getAgentPay(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")int id){
		if(!AuthCheck.UserCheck(request, response, KEYS.PLATFORM)){
			return null;
		}
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = agentPayService.getAgentPayById(id);
			//System.out.println(responseObject.msg);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	//获取代理商缴费记录列表
	@RequestMapping(value="/agent/{id}/pay/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getAgentPaysByAgentId(@PathVariable("id")int agent_id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = agentPayService.getAgentPayListByAgentId(agent_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/agent/{id}/pay",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateAgentPay(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, @RequestBody AgentPay agent_pay){
		/*if(!AuthCheck.UserCheck(request, response,KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			agent_pay.setId(id);
			agentPayService.updateAgentPay(agent_pay);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("agent_pay_id", id);
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
}

















