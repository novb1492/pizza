package com.kim.demo2;



import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class errorRestController {
	
	 private final static org.slf4j.Logger LOGGER=LoggerFactory.getLogger(errorRestController.class);

	    @ExceptionHandler(RuntimeException.class)
	    public JSONObject runtimeException(RuntimeException exception) {
	        LOGGER.info("runtimeException");
	        String message=exception.getMessage();
	        if(!message.startsWith("메")){
	            message="알수 없는 오류발생";
	            exception.printStackTrace();
	        }
	        //exception.printStackTrace();
	        return utillService.makeJson(false, message);
	    }
}
