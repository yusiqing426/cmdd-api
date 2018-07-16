package cn.com.cmdd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
/**
 * @typeName DateConverter
 * @description 
		Summary : TODO 
		Member Property :TODO
		Member Method:TODO
 * @author yusiqing
 * @date 2017年6月22日 上午8:36:04
 */
public class DateConverter implements Converter<String,Date>{
	private final SimpleDateFormat yyyyMMDdd= new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final int length_yyyyMMdd = 10;
	private final int length_yyyyMMddHHmmss = 19;
	
	
	@Override
	public Date convert(String dateStr) {
		
		if(dateStr.length() == length_yyyyMMdd){
			try {
				return yyyyMMDdd.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(dateStr.length() == length_yyyyMMddHHmmss)
			try {
				return yyyyMMddHHmmss.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
}
