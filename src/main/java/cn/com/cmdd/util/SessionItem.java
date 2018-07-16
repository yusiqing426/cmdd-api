
package cn.com.cmdd.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class SessionItem {
	
	private String id;
	private String keys;
	private Date valid_time;
	
	public SessionItem(String id, String keys){
		this.id = id;
		this.keys = keys;
		
		Date date = new Date();
		date.setTime(date.getTime() + 120*60*1000);
		setValid_time(date);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	
	public Date getValid_time() {
		return valid_time;
	}
	public void setValid_time(Date valid_time) {
		this.valid_time = valid_time;
	}
	
	public boolean IsValid(){
		Date d = new Date();
		if(d.after(valid_time))
			return false;
		return true;
	}
	
}
