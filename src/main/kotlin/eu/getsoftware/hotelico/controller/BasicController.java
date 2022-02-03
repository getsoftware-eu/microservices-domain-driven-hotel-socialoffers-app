package eu.getsoftware.hotelico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import eu.getsoftware.hotelico.exception.JsonError;

/**
 * <br/>
 * Created by e.fanshil
 * At 02.02.2016 13:34
 */
public class BasicController
{
	@ExceptionHandler (Exception.class)
	@ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleAllExceptions(Exception ex) {
		return new JsonError(ex.getMessage()).asModelAndView();
	}
}
