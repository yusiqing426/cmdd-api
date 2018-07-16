package cn.com.cmdd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tomcat {
	 public void main(String command) {
         
	        String startUp = "E:\\apache-tomcat-7.0.76\\bin\\startup.bat";//启动tomcat命令
	        //String command = "E:\\apache-tomcat-7.0.76\\bin\\tomcat7w.exe";//启动Tomcat命令，仅限windows版本，无弹框
	        String shutDown = "E:\\apache-tomcat-7.0.76\\bin\\shutdown.bat";//关闭tomcat命令
	       Tomcat callTomcat = new Tomcat();
	        try {
	        	String commandBatPath = "C:\\Users\\Administrator\\Desktop\\cmdd\\apache-tomcat-7.0.88\\bin\\"+command+".bat";
	            callTomcat.callCommand(commandBatPath);
	        } catch (IOException e) {
	            System.out.println("执行命令时出错：" + e.getMessage());
	        }
	         
	    }
	     
	    /**
	     * 执行命令
	     *
	     * @throws IOException
	     */
	    private void callCommand(String command) throws IOException {
	         
	        Runtime runtime = Runtime.getRuntime();//返回与当前的Java应用相关的运行时对象
	        //指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例
	        Process process = runtime.exec(command);
	        runtime.gc();//运行垃圾回收器
	        String line = null;
	        String content = "";
	        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        while((line = br.readLine()) != null) {
	            content += line + "\r\n";
	        }
	        System.out.println(content);
	         
	    }
}
