package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {

	/**
	 * Time Complexity : O(n^2)
	 * @param arr
	 * @return
	 */
	public int[] bubbleSort(int[] arr){

		int n = arr.length;

		for(int i=0; i < n-1; i++){
			for(int j=0; j < n-i-1; j++){
				if(arr[j] > arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}

		return arr;
	}

	/**
	 * Time Complexity : O(n^2)
	 * @param arr
	 * @return
	 */
	public int[] selectionSort(int[] arr){

		int n = arr.length;

		for(int i=0; i<n; i++){
			int min = i;
			for(int j = i+1; j<n; j++){
				if(arr[min] > arr[j]){
					min = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}

		return arr;
	}

	public int[] insertionSort(int[] arr){

		for(int i=1; i< arr.length; i++){

			int target = arr[i];
			int j = i - 1;

			while(j>=0 && target < arr[j]){
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = target;
		}

		return arr;
	}

	public int[] quickSort(int[] arr) {

		quickSort(arr, 0, arr.length-1);

		return arr;
	}

	public void quickSort(int[] arr, int begin, int end){
		int pivot = begin + (end-begin) / 2;


	}



	private int[] toIntArray(List<Integer> list) {
		return list.stream().mapToInt(i -> i).toArray();
	}

	private int[] merge(int[] left, List<Integer> mid, int[] right) {
		int[] result = new int[left.length + mid.size() + right.length];
		int index = 0;

		for (int num : left) {
			result[index++] = num;
		}
		for (int num : mid) {
			result[index++] = num;
		}
		for (int num : right) {
			result[index++] = num;
		}

		return result;
	}

	public static void main(String[] args){

		int[] arr = {3, 5, 4, 8, 2};

		Sort sort = new Sort();
		System.out.println("bubble " + Arrays.toString(sort.bubbleSort(arr)));

		System.out.println(Arrays.toString(sort.selectionSort(arr)));

		// System.out.println(Arrays.toString(sort.insertionSort(arr)));
		//
		// System.out.println(Arrays.toString(sort.quickSort(arr)));
	}
}
