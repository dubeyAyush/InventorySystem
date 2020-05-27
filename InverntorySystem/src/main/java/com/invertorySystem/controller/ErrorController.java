package com.invertorySystem.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invertorySystem.bo.ErrorCodes;
import com.invertorySystem.bo.InventorySystemError;
import com.invertorySystem.exception.ISException;
import com.invertorySystem.utility.ErrorUtility;


@ControllerAdvice
public class ErrorController {

	@Autowired
	ErrorUtility errorUtility;

	//private static final Logger logger = LoggerFactory.getLogger(ErrorController.class.getName());

	@ExceptionHandler(ISException.class)
	@ResponseBody
	public Map handleHabException(HttpServletRequest request, HttpServletResponse response, ISException ex) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return errorUtility.getErrorAsMap(ex.getErrorCodes(),ex.getArgs());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public Map handleHttpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response,
			Exception ex) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return errorUtility.getErrorAsMap(ErrorCodes.REQUEST_BODY_IS_NOT_READABLE,new Object[] {} );
	}


	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map handleAllExceptions(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		Map<String, List<InventorySystemError>> map = errorUtility.getErrorAsMap(ErrorCodes.INTERNAL_ERROR,new Object[] {});
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return map;
	}

}
