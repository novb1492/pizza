package com.mrpizzahut.app;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

public class utillService {
	
    public static RuntimeException makeRuntimeEX(String message,String methodName) {
        throw new RuntimeException("메세지: "+message);
    }
    public static JSONObject makeJson(Boolean flag,String message) {
        JSONObject response=new JSONObject();
        response.put("flag", flag);
        response.put("message", message);
        return response;
    }
    public static boolean checkNull(String s) {
		if(s==null) {
			return true;
		}else if(s.isBlank()) {
			return true;
		}
		return false;
	}
    public static String getEmail(HttpServletRequest request) {
		return (String)request.getSession().getAttribute("email");
	}
}
