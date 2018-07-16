package cn.com.cmdd.controller;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.cmdd.util.ResponseObject;
import cn.com.cmdd.util.SessionItem;
import cn.com.cmdd.util.SessionStore;



public class AuthCheck {
	
	public static boolean UserCheck(HttpServletRequest request, HttpServletResponse response, String requireKeys){
	
		String sessionId = request.getHeader("X-Auth-Token");
		
		if(sessionId == null){
			SendUnauthorized(response);
			return false;
		}
		
		SessionItem session =  SessionStore.Get(sessionId);
		
		if(session == null){
			SendUnauthorized(response);
			return false;
		}
		
		if(!session.IsValid()){
			SessionStore.Delete(sessionId);
			SendUnauthorized(response);
			return false;
		}
		
		
		String[] ownKeys = session.getKeys().split(",");
		
		if(!AllKeysMatch(ownKeys, requireKeys)){
			SendForbidden(response);
			return false;
		}
		
		return true;
		
	}
	
	private static void SendUnauthorized(HttpServletResponse response){
		ResponseObject responseObject = new ResponseObject(ResponseObject.unauthorized, null);
		responseObject.msg="您还没有登录或登录已超时，请重新登录！";
		SendError(response, responseObject);
	}
	
	private static void SendForbidden(HttpServletResponse response){
		ResponseObject responseObject = new ResponseObject(ResponseObject.forbidden, null);
		responseObject.msg="您无权限进行此项操作！";
		SendError(response, responseObject);
	}
	
	private static void SendError(HttpServletResponse response, ResponseObject responseObject){
		ObjectMapper mapper = new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(responseObject);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		response.setContentType("application/json,charset=utf-8");
			
		PrintWriter writer = null;
		try{
			writer = response.getWriter();
			writer.write(result);
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(writer != null){
				writer.close();
			}
		}
		
	}
	
	private static boolean AllKeysMatch(String[] ownKeys, String requiredKeys){
		
		String[] requiredKey =requiredKeys.split(",");
		
		for(int i=0; i<requiredKey.length;i++){
			boolean found = false;
			for(int j=0; j<ownKeys.length;j++){
				if(requiredKey[i].equals(ownKeys[j])){
					found = true;
					break;
				}
			}
			if(!found){
				return false;
			}
		}
		return true;
	}
		
}