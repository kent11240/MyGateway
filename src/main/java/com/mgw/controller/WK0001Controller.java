package com.mgw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgw.constant.ReturnCodes;
import com.mgw.model.BaseResponse;
import com.mgw.model.wkBean.WK0001PersonInfo;
import com.mgw.model.wkBean.WK0001Request;
import com.mgw.model.wkBean.WK0001Response;
import com.mgw.service.I18NService;

@RestController
@RequestMapping(value="/WK0001")
public class WK0001Controller extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(WK0001Controller.class);
	private static final String wkCode = "0001";
	
	@Autowired
    private I18NService i18NService;
	
	@PostMapping("/sayHello")
	public BaseResponse sayHello(@RequestBody @Valid WK0001Request rq) {
		WK0001Response wkBody = new WK0001Response();
		
		String[] args = {rq.getName(), Integer.toString(rq.getAge())};
		String msg = i18NService.fetchMsg("GATEWAY.WK0001.HELLOMSG", args);
		wkBody.setHelloMsg(msg);
		
		return genBaseRs(wkCode, wkBody);
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
		WK0001Response wkBody = new WK0001Response();
		
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#1");
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#2");
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#3");
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#4");
		log.info("print a lot of log........!@#$^$%&^&(&(@$^#%#&^$%%#5");
		
		return genBaseRs(wkCode, wkBody);
	}
	
	@PostMapping("/fetchPerson")
	public BaseResponse fetchPerson(@RequestBody @Valid WK0001Request rq) {
		WK0001Response wkBody = new WK0001Response();
		
		List<WK0001PersonInfo> list = new ArrayList<WK0001PersonInfo>();
		genPersonInfo(list, i18NService.fetchMsg("LBL.WK0001.NAME"), "name", rq.getName());
		genPersonInfo(list, i18NService.fetchMsg("LBL.WK0001.AGE"), "age", Integer.toString(rq.getAge()));
		wkBody.setPersonInfo(list);
		
		return genBaseRs(wkCode, wkBody);
	}
	
	private void genPersonInfo(List<WK0001PersonInfo> list, String label, String key, String value) {
		WK0001PersonInfo personInfo = new WK0001PersonInfo();
		personInfo.setLabel(label);
		personInfo.setKey(key);
		personInfo.setValue(value);
		list.add(personInfo);
	}
	
	@PostMapping("/returnAnError")
	public BaseResponse returnAnError(@RequestBody @Valid WK0001Request rq) {
		WK0001Response wkBody = new WK0001Response();
		
		boolean error = true;
		if (error) {
			return genBaseRs(wkCode, wkBody, ReturnCodes.ERROR, i18NService.fetchMsg("GATEWAY.WK0001.ERRORMSG"));
		}
		
		return genBaseRs(wkCode, wkBody);
	}
}
