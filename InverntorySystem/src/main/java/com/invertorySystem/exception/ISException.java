package com.invertorySystem.exception;

import com.invertorySystem.bo.ErrorCodes;

public class ISException extends Exception {

	/**
	 * Ecllipse generated UID
	 */
	private static final long serialVersionUID = -8342093553812459474L;
	private ErrorCodes errorCodes;
	private Object[] args;
	
	public ISException(ErrorCodes errorCodes) {
		super(errorCodes.toString());
		this.errorCodes = errorCodes;
	}
	
	public ISException(ErrorCodes errorCodes,Object[] args) {
		super(errorCodes.toString());
		this.errorCodes = errorCodes;
		this.args = args;
	}

	public ErrorCodes getErrorCodes() {
		return errorCodes;
	}

	public ISException() {
	}

	public Object[] getArgs() {
		return args;
	}

		
	

}
