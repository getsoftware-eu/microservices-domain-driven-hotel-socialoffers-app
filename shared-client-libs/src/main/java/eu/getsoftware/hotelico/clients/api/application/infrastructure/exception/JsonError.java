package eu.getsoftware.hotelico.clients.api.application.infrastructure.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * <br/>
 * Created by e.fanshil
 * At 02.02.2016 13:28
 */
public class JsonError extends Throwable { 
	
	private final String error;
	private final String message;

	public JsonError(String error, String message){
		this.error = error;
		this.message = message;
	}
	
	public ModelAndView asModelAndView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView ();
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error", message);
		return new ModelAndView(jsonView, errorMap);
	}
} 