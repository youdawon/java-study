/**
 * Easy
 *
 * Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei),
 * determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Copy
 * Input:
 * [[0,30],[5,10],[15,20]]
 * Output:
 *  false
 * Example 2:
 *
 * Copy
 * Input:
 *  [[7,10],[2,4]]
 *
 * Output:
 *  true
 */
package algorithm.leetcode;

import java.util.Arrays;

public class Leetcode252 {

	public static boolean canAttendMeetings(int[][] meetings){

		Arrays.sort(meetings, (a, b) -> (a[0] - b[0]));

		int[] compareTime = meetings[0];
		for(int i = 1; i < meetings.length; i++){
			if(compareTime[1] > meetings[i][0]){
				return false;
			}
			compareTime = meetings[i];
		}
		return true;
	}

	public static void main(String[] args){
		// int[][] meetings = new int[][]{{7,10},{2,4}};
		int[][] meetings = new int[][]{{0,30}, {5,10}, {15,20}};
		System.out.println(canAttendMeetings(meetings));
	}
}
