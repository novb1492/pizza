package com.kim.demo2;

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
}
