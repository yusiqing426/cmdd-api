package cn.com.cmdd.service;

public class ServiceRestart 
{

	public Boolean Restart(String ServiceName) 
	{
        try 
        {
        	Process procStop;   
    		Process procStart;   
    		int stopState = -1;   
    		int startState = -1;   
    		procStop = Runtime.getRuntime().exec("net stop \"" + ServiceName + "\"");   
    	    stopState = GetProcExecStat(procStop);       
    		Thread.sleep(10 * 1000);   
    	    procStart=Runtime.getRuntime().exec("net start \"" + ServiceName + "\"");   
    		startState = GetProcExecStat(procStart);    
    		if (stopState != 0 && startState != 0) 
    		{   
    			System.out.println("重启失败,请确认服务名是否有效,程序将退出...");   
    			return false;
    		} 
    		else 
    		{   
    		    System.out.println("重启成功.");
    		    return true;
    		} 
        } 
        catch(Exception e)
        {
            return false;
        }
        
	}
	
	public int GetProcExecStat(Process Proc)
	{   
	     try 
	     {   
	       return Proc.waitFor();   
	     } 
	     catch (Exception ex)
	     {   
	    	 return -1;   
	     }   
	}   

}
