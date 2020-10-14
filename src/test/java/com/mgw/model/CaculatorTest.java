package com.mgw.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaculatorTest {

	@BeforeAll
	public static void init() {
		System.out.println("BeforeAll");
	}
	
	@BeforeEach
	public void caseStart() {
		System.out.println("BeforeEach");
	}
	
	@Test
	public void testAdd() {
		System.out.println("testAdd");
		
		Calculator calculator = new Calculator();
		int num1 = 5;
		int num2 = 2;
		int expected = 7;
		
		int result = calculator.add(num1, num2);
		
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testMultiply() {
		System.out.println("testMultiply");
		
		Calculator calculator = new Calculator();
		int num1 = 5;
		int num2 = 2;
		int expected = 10;
		
		int result = calculator.multiply(num1, num2);
		
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testDivide() {
		System.out.println("testDivide");
		
		Calculator calculator = new Calculator();
		int num1 = 8;
		int num2 = 2;
		int expected = 4;
		
		int result = calculator.divide(num1, num2);
		
		assertThat(result).isEqualTo(expected);
	}
	
	@AfterEach
	public void caseEnd() {
		System.out.println("AfterEach");
	}
	
	@AfterAll
	public static void finish() {
		System.out.println("AfterAll");
	}
}
