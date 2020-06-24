package com.mgw.contorller;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgw.model.BaseResponse;
import com.mgw.model.wkBean.WK0001Request;
import com.mgw.model.wkBean.WK0001Response;

@RestController
@RequestMapping(value="/WK0001")
public class WK0001Controller {
	private static final Logger log = LoggerFactory.getLogger(WK0001Controller.class);

	private BaseResponse rs = new BaseResponse();
	private WK0001Response wkBody = new WK0001Response();
	
	{
		rs.setWorkCode("0001");
		rs.setWkBody(wkBody);
	}
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping("/sayHello")
	public BaseResponse sayHello(@RequestBody @Valid WK0001Request rq) {
		Locale locale = LocaleContextHolder.getLocale();
		String[] args = {rq.getName(), Integer.toString(rq.getAge())};
		String msg = messageSource.getMessage("GATEWAY.WK0001.HELLOMSG", args, locale);
		wkBody.setHelloMsg(msg);
		return rs;
	}
	
	@PostMapping("/throwNull")
	public BaseResponse returnError(@RequestBody WK0001Request rq) {
		log.error("IT'S");
		log.error("TIME");
		log.error("TO");
		log.error("THROW");
		log.error("NULLPOINTER!!!");
		throw new NullPointerException();
	}
	
	@PostMapping("/printLog")
	public BaseResponse printLog(@RequestBody WK0001Request rq) {
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#1");
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#2");
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#3");
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#4");
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#5");
		return rs;
	}
}
