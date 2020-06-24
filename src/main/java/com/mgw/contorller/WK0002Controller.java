package com.mgw.contorller;

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
public class WK0002Controller {
	
	private BaseResponse rs = new BaseResponse();
	private WK0002Response wkBody = new WK0002Response();
	
	{
		rs.setWorkCode("0002");
		rs.setWkBody(wkBody);
	}
	
	@Autowired
    private AccountService accountService;
	
	@PostMapping("/getAcnt")
	public BaseResponse getAcnt(@RequestBody WK0002Request rq) {
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
		return rs;
	}
	
	@PostMapping("/getAcntById")
	public BaseResponse getAcntById(@RequestBody WK0002Request rq) {
		Account dbAcnt = accountService.findAccountById(rq.getId());

		WK0002Account acnt = new WK0002Account();
		acnt.setAcnt(dbAcnt.getAcnt());
		acnt.setNickName(dbAcnt.getNickname());
		acnt.setAcntType(dbAcnt.getAcntType());
		acnt.setAcntKind(dbAcnt.getAcntKind());
		
		wkBody.setAcnt(acnt);
		return rs;
	}
}
