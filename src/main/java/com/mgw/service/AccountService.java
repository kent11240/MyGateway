package com.mgw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgw.dao.AccountRepository;
import com.mgw.entity.Account;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> findAccountByCustId(String custId) {
		return accountRepository.findByCustIdAndAuthorIdOrderByAcnt(custId, custId);
	}
	
	public Account findAccountById(String id) {
		return accountRepository.findById(id).get();
	}
}
