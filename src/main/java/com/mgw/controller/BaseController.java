package com.mgw.controller;

import com.mgw.constant.ReturnCodes;
import com.mgw.model.BaseResponse;
import com.mgw.model.wkBean.BaseWKBody;

public class BaseController {
	public BaseResponse genBaseRs(String wkCode, BaseWKBody wkBody) {
		return genBaseRs(wkCode, wkBody, ReturnCodes.SUCCESS, "");
	}
	
	public BaseResponse genBaseRs(String wkCode, BaseWKBody wkBody, String rtnCode, String rtnMsg) {
		BaseResponse rs = new BaseResponse();
		rs.setWorkCode(wkCode);
		rs.setRtnCode(rtnCode);
		rs.setRtnMsg(rtnMsg);
		rs.setWkBody(wkBody);
		return rs;
	}
}
