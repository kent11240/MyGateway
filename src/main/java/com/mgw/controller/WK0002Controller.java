package com.mgw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgw.entity.Account;
import com.mgw.model.BaseResponse;
import com.mgw.model.wkBean.WK0002Account;
import com.mgw.model.wkBean.WK0002Request;
import com.mgw.model.wkBean.WK0002Response;
import com.mgw.service.AccountService;

@RestController
@RequestMapping(value="/WK0002")
public class WK0002Controller extends BaseController {
	private static final String wkCode = "0002";
	
	@Autowired
    private AccountService accountService;
	
	@PostMapping("/getAcnt")
	public BaseResponse getAcnt(@RequestBody WK0002Request rq) {
		WK0002Response wkBody = new WK0002Response();
		
		List<Account> dbList = accountService.findAccountByCustId(rq.getCustId());
		
		List<WK0002Account> acntList = new ArrayList<WK0002Account>();
		for (Account entity : dbList) {
			WK0002Account acnt = new WK0002Account();
			acnt.setAcnt(entity.getAcnt());
			acnt.setNickName(entity.getNickname());
			acnt.setAcntType(entity.getAcntType());
			acnt.setAcntKind(entity.getAcntKind());
			acntList.add(acnt);
		}
		
		wkBody.setAcntList(acntList);
		
		return genBaseRs(wkCode, wkBody);
	}
	
	@PostMapping("/getAcntById")
	public BaseResponse getAcntById(@RequestBody WK0002Request rq) {
		WK0002Response wkBody = new WK0002Response();
		
		Account dbAcnt = accountService.findAccountById(rq.getId());

		WK0002Account acnt = new WK0002Account();
		acnt.setAcnt(dbAcnt.getAcnt());
		acnt.setNickName(dbAcnt.getNickname());
		acnt.setAcntType(dbAcnt.getAcntType());
		acnt.setAcntKind(dbAcnt.getAcntKind());
		
		wkBody.setAcnt(acnt);
		
		return genBaseRs(wkCode, wkBody);
	}
}
