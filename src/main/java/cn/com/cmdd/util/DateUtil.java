package cn.com.cmdd.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author ysq19930526
 */
public class DateUtil {

	private final static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static  SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private final static  SimpleDateFormat yyyyIMMIdd = new SimpleDateFormat("yyyy/MM/dd");
	private final static  SimpleDateFormat yy_MM_dd = new SimpleDateFormat("yyMMddHHmmss");
	
	public static String getYyyy_MM_dd(Date date){
		return yyyy_MM_dd.format(date);
	}
	public static String getYyyy_MM_dd_HH_mm_ss(Date date){
		return yyyy_MM_dd_HH_mm_ss.format(date);
	}
	public static String getYyyyMMdd(Date date){
		return yyyyMMdd.format(date);
	}	
	public static String getYyyyMMdd_(Date date){
		return yyyyIMMIdd.format(date);
	}
	public static String getYy_MM_dd(Date date){
		return yy_MM_dd.format(date);
	}
	public static Date yYYYYIMMIDD_ToDate(String formatDate) throws ParseException {
		return yyyyIMMIdd.parse(formatDate);

	}
	
	
}
