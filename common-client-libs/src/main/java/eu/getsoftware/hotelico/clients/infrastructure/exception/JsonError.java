package eu.getsoftware.hotelico.clients.infrastructure.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * <br/>
 * Created by e.fanshil
 * At 02.02.2016 13:28
 */
public class JsonError
{
	private final String message;
	
	public JsonError(String message) {
		this.message = message;
	}
	
	public ModelAndView asModelAndView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView ();
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error", message);
		return new ModelAndView(jsonView, errorMap);
	}
} 