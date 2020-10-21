package com.mgw.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class QuickSortTest {
	@Test
	public void testSort() {
		QuickSort quickSort = new QuickSort();
		int[] nums = {7, 15, 86, -49, 0, 24, -90, 1};
		quickSort.sort(nums);
		System.out.println(Arrays.toString(nums));
		
		int[] expected = {-90, -49, 0, 1, 7, 15, 24, 86};
		assertThat(nums).isEqualTo(expected);
	}
	
	@Test
	public void testDuplicatedNumber() {
		QuickSort quickSort = new QuickSort();
		int[] nums = {-1, 1, -1, 0, -1, 0, 0, 1, -1};
		quickSort.sort(nums);
		System.out.println(Arrays.toString(nums));
		
		int[] expected = {-1, -1, -1, -1, 0, 0, 0, 1, 1};
		assertThat(nums).isEqualTo(expected);
	}
	
	@Test
	public void testSameNumber() {
		QuickSort quickSort = new QuickSort();
		int[] nums = {1, 1, 1, 1, 1, 1, 1};
		quickSort.sort(nums);
		System.out.println(Arrays.toString(nums));
		
		int[] expected = {1, 1, 1, 1, 1, 1, 1};
		assertThat(nums).isEqualTo(expected);
	}
}
