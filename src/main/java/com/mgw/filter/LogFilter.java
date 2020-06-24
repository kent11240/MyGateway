package com.mgw.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.mgw.util.UUIDUtility;

@Component
public class LogFilter extends OncePerRequestFilter {
	private static final Logger wkDataLog = LoggerFactory.getLogger("wkData");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		try {
			MDC.put("trackId", UUIDUtility.getRndUUID(8));
			wkDataLog.info("====================================================");

			long startTime = System.currentTimeMillis();
			ContentCachingRequestWrapper rqWrapper = new ContentCachingRequestWrapper(request);
			ContentCachingResponseWrapper rsWrapper = new ContentCachingResponseWrapper(response);
			
			filterChain.doFilter(rqWrapper, rsWrapper);
		    
			/**
			 * 若於interceptor拋出例外時，request就不會進入controller，故不會讀取reader
			 * 導致wrapper沒有cachedContent，getContentAsByteArray會為空，所以在這邊多read一次
			 */
			if (rqWrapper.getContentAsByteArray().length == 0) {
				readReader(rqWrapper);
			}
		    
			wkDataLog.info(String.format("Request URI   : %s", rqWrapper.getRequestURI()));
			wkDataLog.info(String.format("Request Body  : %s", new String(rqWrapper.getContentAsByteArray())));
			
			wkDataLog.info(String.format("Response Body : %s", new String(rsWrapper.getContentAsByteArray())));
			rsWrapper.copyBodyToResponse();
			
			long processTime = System.currentTimeMillis() - startTime;
			wkDataLog.info(String.format("Process time  : %dms", processTime));

			wkDataLog.info("====================================================");
		} finally {
			MDC.remove("trackId");
		}
	}
	
	/**
	 * 讀取request.reader
	 */
	private void readReader(ContentCachingRequestWrapper rqWrapper) throws IOException {
		BufferedReader reader = rqWrapper.getReader();
	    try {
	        while (reader.readLine() != null) {
	        }
	    } finally {
	        reader.close();
	    }
	}
}
