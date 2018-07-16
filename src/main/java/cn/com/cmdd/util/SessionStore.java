
package cn.com.cmdd.util;

import java.util.HashMap;
import java.util.Map;

public class SessionStore {
	public static Map<String,SessionItem> SessionMap = new HashMap<String,SessionItem>();
    
	public static void Add(String key, SessionItem value){
		SessionMap.put(key, value);
	}
	public static void Delete(String key){
		SessionMap.remove(key);
	}
	public static SessionItem Get(String key){
		return SessionMap.get(key);
	}
	
}
