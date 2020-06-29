package com.mgw.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class I18NService {
	@Autowired
	private MessageSource messageSource;
	
	public String fetchMsg(String msgCode) {
		return fetchMsg(msgCode, null);
	}
	
	public String fetchMsg(String msgCode, String[] args) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(msgCode, args, locale);
	}
}
