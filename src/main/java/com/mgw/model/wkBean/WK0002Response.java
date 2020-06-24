package com.mgw.model.wkBean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class WK0002Response extends BaseWKBody {
	@Getter @Setter
	private List<WK0002Account> acntList;
	
	@Getter @Setter
	private WK0002Account acnt;
}
