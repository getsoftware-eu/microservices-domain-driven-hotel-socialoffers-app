package eu.getsoftware.hotelico.infrastructure.hotel.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.common.collect.ImmutableMap;

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
		return new ModelAndView(jsonView, ImmutableMap.of("error", message));
	}
} 