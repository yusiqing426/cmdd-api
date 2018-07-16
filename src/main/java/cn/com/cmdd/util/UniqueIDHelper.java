package cn.com.cmdd.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UniqueIDHelper {

	public static String GenernateDateString(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
	public static String GenernateRandNumber(int length){
		Random rand = new Random();
		StringBuilder sb = new StringBuilder(length);  
	    for(int i=0; i < length; i++)  
	    {
	        sb.append((char)('0' + (rand.nextInt(9))+1));  
	    }
	    return sb.toString();  
	}
	public static String GenernateOrderID(){
		return GenernateDateString() + GenernateRandNumber(3);
	}
	
	public static String GenernateDepositID(){
		return "d"+GenernateDateString() + GenernateRandNumber(2);
	}

}