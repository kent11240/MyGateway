package com.mgw.model.wkBean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

public class WK0001Request {
	@Getter @Setter
	@NotEmpty
	private String name;
	
	@Getter @Setter
	@Min(value = 0)
	private int age;
}
