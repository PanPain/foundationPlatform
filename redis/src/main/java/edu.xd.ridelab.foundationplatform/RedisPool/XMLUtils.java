package edu.xd.ridelab.foundationplatform.RedisPool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class XMLUtils {
	
	//当前Redis的访问IP
	private static String writeIP="";
	//只读Redis的IP地址
	private static String readIP="";
	//redis的访问端口
	private static int port=0;
	
	private static InputStream is;
	//redis获取连接的超时设置
	private static int TimeOutMills=5000;
	
	static{

		try{

			Properties prop=new Properties();
			is = XMLUtils.class.getResourceAsStream("/RedisConfig.properties");
			prop.load(is);
			
			writeIP=prop.getProperty("REDIS_WRITE_IP");
			
			readIP=prop.getProperty("REDIS_READ_IP");
			
			port=Integer.valueOf(prop.getProperty("REDIS_MONITOR_PORT"));
			//redis连接的超时时间
			TimeOutMills=Integer.valueOf(prop.getProperty("REDIS_CONN_TIME_OUT_MILLS"));
			
			System.out.println("Redis write IP: "+writeIP+",Redis read IP: "+readIP+",monitor port: "+port+" timeout: "+TimeOutMills);
			
		}catch(Exception e){
			
			System.out.println("Error get config file: "+e.getMessage());
			
			e.printStackTrace();
		}
		finally{
			
			try{
				
				if(is!=null){
					
					is.close();
				}
				
			}catch(Exception e){
				
				System.out.println("Error closing config file: "+e.getMessage());
				
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static String getWriteIP(){
		
		return writeIP;
	}
	
	public static String getReadIP(){
		
		return readIP;
	}
	
	public static int getPort(){
		
		return port;
	}
	//获取jedis连接的超时时间
	public static int getTimeOutMills(){
		
		return TimeOutMills;
	}
	

}
