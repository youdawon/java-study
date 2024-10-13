package algorithm.dp;

import java.util.Arrays;

public class RobotInGrid {

	public static int getCount(int[][] grid, int[][] dp, int i, int j){

		if(i < 0 || j < 0){
			return 0;
		}

		if(dp[i][j] != -1){
			return dp[i][j];
		}

		grid[i][j] = getCount(grid, dp, i-1, j) + getCount(grid, dp, i, j-1);

		return grid[i][j];
	}

	public static void main(String[] args){
		int[][] grid = {
			{0, 0, 0},
			{0, 1, 0},
			{0, 0, 0}
		};

		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];

		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				dp[i][j] = -1;
			}
		}

		for(int i=1; i<grid.length; i++){
			if(grid[i][0] != 1){
				dp[i][0] = 1;
			}
		}

		for(int j=1; j<grid[0].length; j++){
			if(grid[0][j] != 1){
				dp[0][j] = 1;
			}
		}

		if(grid[0][0] == 0)	dp[0][0] = 0;

		getCount(grid, dp, 0, 0);

		System.out.println(dp[m-1][n-1]);
	}
}
