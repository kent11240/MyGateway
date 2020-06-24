package com.mgw.exception;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

import com.mgw.constant.ReturnCodes;
import com.mgw.model.BaseResponse;

@ControllerAdvice
public class MyExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> validExceptionHandler(MethodArgumentNotValidException vex, HandlerMethod handlerMethod) {
		Locale locale = LocaleContextHolder.getLocale();
		
		log.error("Parameter validation failed：");
		for (ObjectError error : vex.getBindingResult().getAllErrors()) {
			log.error(String.format("key:[%s], msg:[%s]", ((FieldError) error).getField(), error.getDefaultMessage()));
		}
		
		BaseResponse rs = generateErrorResponse(handlerMethod, messageSource.getMessage("GATEWAY.EXCP.VALID", null, locale));
        return new ResponseEntity<BaseResponse>(rs, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@ExceptionHandler(GatewayException.class)
    public ResponseEntity<BaseResponse> gatewayExceptionHandler(GatewayException gex, HandlerMethod handlerMethod) {
		Locale locale = LocaleContextHolder.getLocale();
		
		log.error("GatewayException：" + gex.getRtnMsgCode());
		
		BaseResponse rs = generateErrorResponse(handlerMethod, messageSource.getMessage(gex.getRtnMsgCode(), null, locale));
        return new ResponseEntity<BaseResponse>(rs, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> exceptionHandler(Exception ex, HandlerMethod handlerMethod) {
		Locale locale = LocaleContextHolder.getLocale();
		
		log.error("Unexpected error occurred：", ex);
		
		BaseResponse rs = generateErrorResponse(handlerMethod, messageSource.getMessage("GATEWAY.EXCP.UNEXCP", null, locale));
        return new ResponseEntity<BaseResponse>(rs, new HttpHeaders(), HttpStatus.OK); 
    }
	
	private BaseResponse generateErrorResponse(HandlerMethod handlerMethod, String rtnMsg) {
		BaseResponse rs = new BaseResponse();
		rs.setWorkCode(fetchWorkCode(handlerMethod));
		rs.setRtnCode(ReturnCodes.ERROR);
		rs.setRtnMsg(rtnMsg);
		return rs;
	}
	
	private String fetchWorkCode(HandlerMethod handlerMethod) {
		String workCode = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class).value()[0];
		int indexS = workCode.indexOf("WK") + 2;
		int indexE = indexS + 4;
		return workCode.substring(indexS, indexE);
	}
}
