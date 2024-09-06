package eu.getsoftware.hotelico.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.infrastructure.exception.JsonError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * It is wrong way, to have only one internal error!!!
 * <br/>
 * Created by e.fanshil
 * At 02.02.2016 13:34
 */
public class BasicController
{
	@ExceptionHandler (Exception.class)
	//Eugen: all Exceptions have only the same HttpCode = INTERNAL_SERVER_ERROR ????
	@ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleAllExceptions(Exception ex) {
		return new JsonError(ex.getMessage()).asModelAndView();
	}
}
