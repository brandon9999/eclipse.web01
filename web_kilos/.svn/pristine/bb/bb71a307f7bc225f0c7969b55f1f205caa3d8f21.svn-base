package com.javacan.mvc.ana;

import java.util.Map;
import com.javacan.mvc.configuration.Configuration;

public class AAManagerFactory 
{
    private static Configuration configuration;
    private static Map repository = new java.util.HashMap();
    
    public static void setConfiguration(Configuration conf) 
    {
        configuration = conf;
    }
    
    public static AAManager createAAManager(String className)
    throws AAManagerException 
    {
        AAManager aaManager = (AAManager)repository.get(className);
        if (aaManager == null) 
        {
        
        	/*
        	synchronized(repository) 
            {
                aaManager = (AAManager)repository.get(className);
                if (aaManager == null) 
                {
                    try 
                    {
                        Class aaManagerClass = Class.forName(className);
                        aaManager = (AAManager)aaManagerClass.newInstance();
                    } 
                    catch(Throwable e) 
                    {
                        throw new AAManagerException(e.getMessage(), e);
                    }
                }
            }
        	*/
        	
        	
            //synchronized(repository) 
            //{
        	
            	System.out.println("[TEST] ===========================================================");
            	System.out.println("[TEST] synchronized");
            	System.out.println("[TEST] ===========================================================");
            	
                aaManager = (AAManager)repository.get(className);
                if (aaManager == null) 
                {
                    try 
                    {
                        Class aaManagerClass = Class.forName(className);
                        aaManager = (AAManager)aaManagerClass.newInstance();
                    } 
                    catch(Throwable e) 
                    {
                        throw new AAManagerException(e.getMessage(), e);
                    }
                }
            //}
 
                
                
        }
        return aaManager;
    }
    
    
    public static AAManager createAAManager() 
    throws AAManagerException 
    {
        if (configuration != null && 
            configuration.getAaManagerClassName() != null &&
            !configuration.getAaManagerClassName().equals("")) 
        {
            return AAManagerFactory.createAAManager(configuration.getAaManagerClassName());
        } 
        else 
        {
            return AAManagerFactory.createAAManager(
                System.getProperty("com.javacan.mvc.ana.AAManager.class"));
        }
    }
}
