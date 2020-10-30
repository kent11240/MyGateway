package com.mgw.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class FibonacciTest {
	@Test
	public void testZeroLength() {
		Fibonacci fib = new Fibonacci();
		int length = 0;
		int[] result = fib.genFibonacciSeq(length);
		System.out.println(Arrays.toString(result));
		
		int[] expected = {};
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testOneLength() {
		Fibonacci fib = new Fibonacci();
		int length = 1;
		int[] result = fib.genFibonacciSeq(length);
		System.out.println(Arrays.toString(result));
		
		int[] expected = {0};
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testTwoLength() {
		Fibonacci fib = new Fibonacci();
		int length = 2;
		int[] result = fib.genFibonacciSeq(length);
		System.out.println(Arrays.toString(result));
		
		int[] expected = {0, 1};
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testAnyNumber() {
		Fibonacci fib = new Fibonacci();
		int length = 10;
		int[] result = fib.genFibonacciSeq(length);
		System.out.println(Arrays.toString(result));
		
		int[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
		assertThat(result).isEqualTo(expected);
	}
}
