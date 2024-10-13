package main.algorithm.dp;

import java.util.Arrays;

public class Steps {

	public static int steps(int n, int[] dp){
		if(n < 0){
			return 0;
		} else if(n == 0){
			return 1;
		}

		if(dp[n] != -1){
			return dp[n];
		}

		dp[n] = steps(n-1, dp) + steps(n-2, dp) + steps(n-3, dp);

		return dp[n];
	}

	public static void main(String[] args){

		int n = 36;
		int[] dp = new int[n+1];
		Arrays.fill(dp, -1);
		System.out.println(steps(n, dp));
	}
}
