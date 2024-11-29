package com.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicProgramming {

	Map<Integer, Integer> fibMap = new HashMap<>();

	public int fibMemoization(int n){

		if(n < 2)
			return n;

		int total = 0;

		if(!fibMap.containsKey(n)){
			return  fibMemoization(n-1) + fibMemoization(n-2);
		} else {
			return fibMap.get(n);
		}
	}

	public int fibMemoization2(int n) {

		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);

		for (int i = 2; i <= n; i++) {
			list.add(list.get(i-1) + list.get(i-2));
		}
		return list.get(n);
	}

	public static void main(String[] args){
		DynamicProgramming dynamicProgramming = new DynamicProgramming();

	//	System.out.println(dynamicProgramming.fibMemoization(5));
		System.out.println(dynamicProgramming.fibMemoization2(5));
	}
}
