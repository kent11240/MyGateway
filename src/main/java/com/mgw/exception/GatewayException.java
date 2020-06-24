package com.mgw.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GatewayException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private final String rtnCode;
	
	@Getter
	private final String rtnMsgCode;
}
