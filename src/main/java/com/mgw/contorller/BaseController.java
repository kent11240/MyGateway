package com.mgw.contorller;

import com.mgw.model.BaseResponse;
import com.mgw.model.wkBean.BaseWKBody;

public class BaseController {
	public BaseResponse genBaseRs(String wkCode, BaseWKBody wkBody) {
		BaseResponse rs = new BaseResponse();
		rs.setWorkCode(wkCode);
		rs.setWkBody(wkBody);
		return rs;
	}
}
