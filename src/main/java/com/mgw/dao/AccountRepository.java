package com.mgw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgw.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
	List<Account> findByCustIdAndAuthorIdOrderByAcnt (String custId, String authorId);
}
