package eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * <br/>
 * Created by e.fanshil
 * At 02.02.2016 13:28
 */
@RequiredArgsConstructor
public class JsonError extends Throwable { 
	
	private final String error;
	private final String message;
	
	public ModelAndView asModelAndView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView ();
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error", message);
		return new ModelAndView(jsonView, errorMap);
	}
} 