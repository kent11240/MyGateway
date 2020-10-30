package com.mgw.model;

public class Fibonacci {
	public int[] genFibonacciSeq(int length) {
		if (length < 0) {
			return null;
		}
		
		int[] seq = new int[length];
		for (int i = 0; i < seq.length; i++) {
			seq[i] = fibonacci(i);
		}
		
		return seq;
	}
	
	private int fibonacci(int index) {
		if (index <= 1) return index;
		return fibonacci(index - 1) + fibonacci(index - 2);
	}
}
