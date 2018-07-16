package cn.com.cmdd.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.cmdd.domain.Agent;
import cn.com.cmdd.service.AgentService;
import cn.com.cmdd.util.ResponseObject;

@Controller/*@CrossOrigin(origins = "*")*/
public class AgentController {
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AgentController.class);
	
	@Autowired
	private AgentService agentService;
	

	@RequestMapping(value="/agent/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getAgents(){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = agentService.getAgentList();
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	//增加代理商
	@RequestMapping(value="/agent",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addAgent(HttpServletRequest request,HttpServletResponse response,@RequestBody Agent agent){
		
//		if(!AuthCheck.UserCheck(request, response,KEYS.PLATFORM)){
//			return null;
//		} 
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			Integer id = agentService.addAgent(agent);
			
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("agent_id", id);
			responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	//得到代理商,根据id
	@RequestMapping(value="/agent/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getAgent(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")int id){
		
//		if(!AuthCheck.UserCheck(request, response,KEYS.PLATFORM)){
//		return null;
//	} 
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = agentService.getAgent(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/agent/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateAgent(
										HttpServletRequest request, 
										HttpServletResponse response, 
										@PathVariable("id") int id, 
										@RequestBody Agent agent
									){
//		if(!AuthCheck.UserCheck(request, response)){
//			return null;
//		}
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			//agent.setId(id);
			agentService.updateAgent(agent);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("agent_id", id);
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	/*//代理商重置密码
	@RequestMapping(value="/agent/{id}/resetPassword",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject resetPassword(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, @RequestBody Agent agent){
//		if(!AuthCheck.UserCheck(request, response)){
//			return null;
//		}
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			//agent.setId(id);
			agentService.resetPassword(agent);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("agent_id", id);
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}*/
	
}



















