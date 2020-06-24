package com.mgw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(schema = "CNB", name = "ACCOUNT")
public @Data class Account {
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "CUST_ID")
	private String custId;

	@Column(name = "AUTHOR_ID")
	private String authorId;
	
	@Column(name = "ACNT_TYPE")
	private String acntType;
	
	@Column(name = "SBJT_CODE")
	private String sbjtCode;
	
	@Column(name = "ACNT_KIND")
	private String acntKind;
	
	@Column(name = "ACNT_BRN_ID")
	private String acntBrnId;
	
	@Column(name = "ACNT")
	private String acnt;
	
	@Column(name = "SSL_AMT")
	private String sslAmt;
	
	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "CRTE_BRN_ID")
	private String crteBrnId;
	
	@Column(name = "CRTE_ID")
	private String crteId;
	
	@Column(name = "CRTE_DTTM")
	private String crteDttm;
	
	@Column(name = "APPR_ID")
	private String apprId;
	
	@Column(name = "APPR_DTTM")
	private String apprDttm;
	
	@Column(name = "TEMP_ID")
	private String tempId;
	
	@Column(name = "BACKGROUND_COLOR")
	private String backgroundColor;
	
	@Column(name = "COMPANY")
	private String company;
}
