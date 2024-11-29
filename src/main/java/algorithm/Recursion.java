package com.algorithms;

public class Recursion {

	public int findFactorialRecursive(int num){

		if(num == 1)
			return num;

		return num * findFactorialRecursive(num-1);
	}

	public int findFactorialIterative(int num){

		int result = 1;

		for(int i = num; i > 0; i--){
			result = result * i;
		}

		return result;
	}

	public int findFibonacciRecursive(int num){

		if(num == 0 || num == 1)
			return num;

		return findFibonacciRecursive(num-1) + findFibonacciRecursive(num-2);
	}

	public int findFibonacciIterative(int num){

		int prev = 0;
		int result = 1;

		for(int i=2; i<=num; i++){
			int temp = result;
			result = prev + result;
			prev = temp;
		}

		return result;
	}

	public static void main(String[] args){

		Recursion recursion = new Recursion();

		System.out.println(recursion.findFactorialRecursive(5));
		System.out.println(recursion.findFactorialIterative(5));
		System.out.println(recursion.findFibonacciRecursive(5));
		System.out.println(recursion.findFibonacciIterative(5));
	}
}
