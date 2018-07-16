
package cn.com.cmdd.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huoshan on 17-1-10.
 */
public class ResponseObject {
    public static final String ok = "200";
    public static final String FAIL = "400";
    public static final String notFound = "404";
    public static final String unauthorized = "401";
    public static final String forbidden = "403";
    public static final String serverError = "500";
    public static final String parameterUnconformity = "250";
    
    public String code;
    public Object msg;

    public ResponseObject(String code,Object msg){
        if(msg instanceof String){
            String[] msgs = msg.toString().split(",");
            Map<String,Object> resultMap = new HashMap<String,Object>();
            for(String str : msgs){
                String[] strs = str.split(":");
                resultMap.put(strs[0],strs[1]);
            }
            this.msg = resultMap;
        }else {
            this.msg = msg;
        }
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Object getMsg() {
        return msg;
    }
    public void setMsg(Object msg) {
        if(msg instanceof String){
            String[] msgs = msg.toString().split(",");
            Map<String,Object> resultMap = new HashMap<String,Object>();
            for(String str : msgs){
                String[] strs = str.split(":");
                resultMap.put(strs[0],strs[1]);
            }
            this.msg = resultMap;
        }else {
            this.msg = msg;
        }
    }
}
