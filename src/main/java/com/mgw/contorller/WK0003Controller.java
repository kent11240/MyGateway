package com.mgw.contorller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgw.model.BaseResponse;
import com.mgw.model.wkBean.WK0003Request;
import com.mgw.model.wkBean.WK0003Response;
import com.mgw.util.HTTPUtility;

@RestController
@RequestMapping(value = "/WK0003")
public class WK0003Controller extends BaseController {
	private static final String wkCode = "0003";
	
	@Autowired
	private Environment env;
	
	@PostMapping("/sendHello")
	public BaseResponse sendHello(@RequestBody WK0003Request rq) {
		WK0003Response wkBody = new WK0003Response();

		String urlStr = env.getProperty("url.hello");
		
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Accept-Language", "en-US");
		
		String requestBody = String.format("{\"name\":\"%s\",\"age\":%d}", rq.getName(), rq.getAge());
		
		String responseBody = HTTPUtility.doHTTPRequest(HTTPUtility.METHOD_POST, urlStr, header, requestBody);
		wkBody.setResult(responseBody);

		return genBaseRs(wkCode, wkBody);
	}
	
	@PostMapping("/sendBank")
	public BaseResponse sendBank(@RequestBody WK0003Request rq) {
		WK0003Response wkBody = new WK0003Response();

		String urlStr = env.getProperty("url.bank");
		
		HashMap<String, String> header = new HashMap<String, String>();
		
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		String requestBody = HTTPUtility.genParamsData(params);
		
		String responseBody = HTTPUtility.doHTTPRequest(HTTPUtility.METHOD_POST, urlStr, header, requestBody);
		wkBody.setResult(responseBody);

		return genBaseRs(wkCode, wkBody);
	}
	
	@PostMapping("/sendJSON")
	public BaseResponse sendJSON(@RequestBody WK0003Request rq) {
		WK0003Response wkBody = new WK0003Response();

		String urlStr = env.getProperty("url.json.users");
		
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("id", rq.getId());
		urlStr += "?" + HTTPUtility.genParamsData(params);
		
		String responseBody = HTTPUtility.doHTTPRequest(HTTPUtility.METHOD_GET, urlStr, null, null);
		wkBody.setResult(responseBody);

		return genBaseRs(wkCode, wkBody);
	}
}
