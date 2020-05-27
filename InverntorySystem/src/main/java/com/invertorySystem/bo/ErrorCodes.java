package com.invertorySystem.bo;

public enum ErrorCodes {
	INTERNAL_ERROR (1001),
	USERNAME_OR_PASSWORD_IS_INVALID (1002),
	METHOD_TYPE_NOT_SUPPORTED(1003),
	USERNAME_NOT_FOUND (1004),
	NO_RECORD_FOUND(1005),
	REQUEST_BODY_IS_NOT_READABLE(1006),
	EMPTY_REQUEST(1007),
	INVALID_USERNAME(1008),
	MESSAGE_NOT_FOUND(1009),
	USER_ALREADY_EXISTS(1010),
	EMPTY_CART(1011),
	QUANTITY_NOT_AVAILABLE(1012);
    
	
	private final int value;
    
    ErrorCodes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
