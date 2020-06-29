package com.mgw.model.wkBean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class WK0001Response extends BaseWKBody {
	@Getter @Setter
	private String helloMsg;
	
	@Getter @Setter
	private List<WK0001PersonInfo> personInfo;
}
