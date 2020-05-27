package com.invertorySystem.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invertorySystem.bo.ErrorCodes;
import com.invertorySystem.bo.InventorySystemError;
import com.invertorySystem.service.impl.Messages;

@Component
public class ErrorUtility {
	
	
	@Autowired
	Messages messages;
	
	public InventorySystemError getError(ErrorCodes errorCode,Object[] args){
		InventorySystemError error = new InventorySystemError(errorCode.getValue(), messages.get(errorCode.toString()));
		return error;
	}
	
	public List<InventorySystemError> getErrorAsList(ErrorCodes errorCode,Object[] args){
		String message = null;
		if(args != null)
			message = messages.getMessageWithArgs(errorCode.toString(), args);
		else
			message = messages.get(errorCode.toString());
		
		
		InventorySystemError error = new InventorySystemError(errorCode.getValue(), message);
		return Collections.singletonList(error);
	}	

	public Map<String, List<InventorySystemError>> getErrorAsMap(ErrorCodes errorCode,Object[] args){
		Map<String,List<InventorySystemError>> map = new HashMap<String,List<InventorySystemError>>();
		map.put("apiInventorySystemError", getErrorAsList(errorCode,args));
		return map;
	}
	
	public List<InventorySystemError> getErrorAsList(ErrorCodes errorCode,String exceptionMsg){
		List<InventorySystemError> errorList=new ArrayList<InventorySystemError>();
		InventorySystemError error1 = new InventorySystemError(1010, exceptionMsg);
		InventorySystemError error2 = new InventorySystemError(errorCode.getValue(), messages.get(errorCode.toString()));
		errorList.add(error2);
		errorList.add(error1);
		return errorList;
	}	
	
	public Map<String, List<InventorySystemError>> getErrorAsMap(ErrorCodes errorCode,String exceptionMsg){
		Map<String,List<InventorySystemError>> map = new HashMap<String,List<InventorySystemError>>();
		map.put("apiInventorySystemError", getErrorAsList(errorCode,exceptionMsg));
		return map;
	}

}
