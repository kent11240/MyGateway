package com.mgw.model;

import com.mgw.model.wkBean.BaseWKBody;

import lombok.Getter;
import lombok.Setter;

public class BaseResponse {
	@Getter @Setter
	private String workCode;
	
	@Getter @Setter
	private String rtnCode;
	
	@Getter @Setter
	private String rtnMsg;
	
	@Getter @Setter
	private BaseWKBody wkBody;
}
