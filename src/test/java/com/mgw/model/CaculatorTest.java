package com.mgw.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaculatorTest {
	
	private static Calculator cal;
	private int num1;
	private int num2;
	
	@BeforeAll
	public static void init() {
		System.out.println("BeforeAll");
		cal = new Calculator();
	}
	
	@BeforeEach
	public void rndNum() {
		System.out.println("BeforeEach");
		num1 = (int) (Math.random() * 100);
		num2 = (int) (Math.random() * 100);
	}
	
	@Test
	public void addTest() {
		System.out.println("addTest");
		
		int result = cal.add(num1, num2);
		
		assertThat(result).isEqualTo(num1 + num2);
	}
	
	@Test
	public void multiplyTest() {
		System.out.println("multiplyTest");
		
		int result = cal.multiply(num1, num2);
		
		assertThat(result).isEqualTo(num1 * num2);
	}
	
	@Test
	public void divideTest() {
		System.out.println("divideTest");
		
		int result = cal.divide(num1, num2);
		
		assertThat(result).isEqualTo(num1 / num2);
	}
	
	@AfterEach
	public void caseEnd() {
		System.out.println("AfterEach");
		System.out.println(String.format("num1:%d, num2:%d", num1, num2));
	}
	
	@AfterAll
	public static void finish() {
		System.out.println("AfterAll");
	}
}
