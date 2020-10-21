package com.mgw.model;

public class QuickSort {
	public void sort(int[] nums) {
		quicksort(nums, 0, nums.length - 1);
	}
	
	private void quicksort(int[] nums, int left, int right) {
		if (left < right) {
			// 找一個pivot並交換到最終index
			int pivotIndex = partition(nums, left, right);
			
			// 處理剩下pivot的左邊及右邊
			quicksort(nums, left, pivotIndex - 1);
			quicksort(nums, pivotIndex + 1, right);
		}
	}
	
	private int partition(int[] nums, int left, int right) {
		int i = left - 1; // 下一個比pivot小的數要放的index
		int pivotValue = nums[right]; // 以最右邊的數為pivot
		
		// 將<=pivot的數交換到左邊
		for (int j = left; j < right; j++) {
			if (nums[j] <= pivotValue) {
				i++;
				swap(nums, i, j);
			}
		}
		
		// 下一個i就是pivot最終的index，交換
		int pivotIndex = i + 1;
		swap(nums, pivotIndex, right);
		
		return pivotIndex;
	}
	
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
