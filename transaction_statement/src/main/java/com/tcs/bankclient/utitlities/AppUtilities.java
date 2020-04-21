package com.tcs.bankclient.utitlities;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.bankclient.exceptiohandling.ExceptionResponse;
import com.tcs.bankclient.model.Transaction;


public class AppUtilities {
	public static String stringEncription(String str)
	{
		int i;
		String result="";
	    for(i=0;i<str.length();i++)
	    {
	        char ch=str.charAt(i);
	        int x=ch;
	        x=x+8;
	        result=result+((char)x);
	        
	    }
	    return result;
	}

	public static String jsonToString(List<?> record) throws Exception {
		String jsonStr="";
		 ObjectMapper Obj = new ObjectMapper(); 
		  
	        try 
	        { 
	            jsonStr = Obj.writeValueAsString(record); 
	        } 
	  
	        catch (IOException e) { 
	           throw new Exception(); 
	        } 
	        return jsonStr;
		
	}

	public static String userEncrypt(String username) {
		String userid=username.substring(0,3)+username.substring(username.length()-2)+Integer.toString((int)( Math.random()*100));
		
		return userid;
	}

	public static String jsonToStringException(ExceptionResponse error) throws Exception {
		String jsonStr="";
		 ObjectMapper Obj = new ObjectMapper(); 
		  
	        try 
	        { 
	            jsonStr = Obj.writeValueAsString(error); 
	        } 
	  
	        catch (IOException e) { 
	           throw new Exception(); 
	        } 
	        return jsonStr;
	}

	public static String jsonToString(Transaction tran) throws Exception
	{
		String jsonStr="";
		 ObjectMapper Obj = new ObjectMapper(); 
		  
	        try 
	        { 
	            jsonStr = Obj.writeValueAsString(tran); 
	        } 
	  
	        catch (IOException e) { 
	           throw new Exception(); 
	        } 
	        return jsonStr;
	}
		
	

}
