package com.beitu.saas.risk.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 获取配置文件config中指定key的值
 *
 */

public class Config {
    private static Logger log = LoggerFactory.getLogger(Config.class);
	private static Properties resources=null;
	private static Properties namedResources = null;
	
	private static String type;

	public static void setType(String type) {
		Config.type = type;
	}

	private static void getBundle(){
        try {
        	Resource resource;
        	if (null != type){
        		resource = new ClassPathResource(type+"/config.properties");
        	}else{
        		resource = new ClassPathResource("produce/config.properties");
        	}
            resources = PropertiesLoaderUtils.loadProperties(new EncodedResource(resource, "UTF-8"));
        } catch (IOException mre) {
            log.error(mre.toString(), mre);
        }
    }
	
	private static void getNamedBundle(String filename){
		try {
		    Resource resource = new ClassPathResource(filename);
			namedResources = PropertiesLoaderUtils.loadProperties(new EncodedResource(resource, "UTF-8"));
        } catch (IOException mre) {
        	log.error(mre.toString(), mre);
        }
	}
	
	public static String getValue(String key, String filename){
		getNamedBundle(filename);
    	try{
    		return namedResources.getProperty(key);
		}catch(Exception e){
			return null;
		}
    }
    
    private synchronized static boolean checkResources(){
    	if(resources==null)
    		getBundle();
    	return (resources!=null);
    }
    
    
    private static boolean changeToBoolean(String str)throws Exception{
    	String tmp = str.toLowerCase();
    	if(tmp.equals("true"))
    		return true;
    	else if(tmp.equals("false"))
    		return false;
    	else
    		throw new Exception("不能找到资源文件");
    }
    
    public static boolean getBoolean(String key){
    	String str = getString(key);
    	try{
    		return changeToBoolean(str);
    	}catch(Exception e){
    		return false;
    	}
    }
    
    public static boolean getBoolean(String key,boolean defaultValue){
    	String str = getString(key);
    	try{
    		return changeToBoolean(str);
    	}catch(Exception e){
    		return defaultValue;
    	}
    }
    
    
    private static int changeToInt(String str)throws Exception {
    	return Integer.parseInt(str);
    }
    
    public static int getInt(String key){
    	String str = getString(key);    	
    	try{
    		return changeToInt(str);
    	}catch(Exception e){
    		return 0;
    	}   		
    }
    
    public static long getLong(String key){
    	String str = getString(key);    	
    	try{
    		return Long.parseLong(str);
    	}catch(Exception e){
    		return 0;
    	}
    }
    
    public static int getInt(String key,int defaultValue){
    	String str = getString(key);    	
    	try{
    		return changeToInt(str);
    	}catch(Exception e){
    		return defaultValue;
    	}   		
    }
    
    
    
    public static String getString(String key,String defaultValue){
    	String tmp = null;
    	if(checkResources()){
    		try{
	    		tmp = resources.getProperty(key);
    		}catch(Exception e){
    			tmp = defaultValue;
    		}
    	}    
	    return tmp;
    }
    
    public static String getString(String key){
    	if(checkResources()){    	
    		try{
	    		return resources.getProperty(key);
    		}catch(Exception e){
    			;
    		}
    	}
	    return null;
    }
    
    @SuppressWarnings("rawtypes")
	public static Enumeration getKeys(){
    	return resources.keys();
    }
}
