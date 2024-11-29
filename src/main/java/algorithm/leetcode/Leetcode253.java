/**253. Meeting Rooms II
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
	(si < ei), find the minimum number of conference rooms required.

	Example 1:

	Input: [[0, 30],[5, 10],[15, 20]]
	Output: 2
	Example 2:

	Input: [[7,10],[2,4]]
	Output: 1
**/
package algorithm.leetcode;

import java.util.Arrays;

public class Leetcode253 {

	public static int minMeetingRooms(int[][] meetings){

		int rooms = 0;

		int[] start = new int[meetings.length];
		int[] end = new int[meetings.length];

		for(int i=0; i<meetings.length; i++){
			start[i] = meetings[i][0];
			end[i] = meetings[i][1];
		}

		Arrays.sort(start);
		Arrays.sort(end);

		int startPointer = 0;
		int endPointer = 0;
		while(startPointer < start.length){
			if(start[startPointer] < end[endPointer]){
				rooms++;
			} else {
				endPointer++;
			}
			startPointer++;
		}

		return rooms;

	}

	public static void main(String[] args){
		// Test case 1
		int[][] meetings1 = new int[][]{{7,10},{2,4}};
		System.out.println(minMeetingRooms(meetings1));
		int[][] meetings2 = new int[][]{{0,30}, {5,10}, {15,20}};
		System.out.println(minMeetingRooms(meetings2));
		int[][] meetings3 = new int[][]{{1, 4}, {2, 5}, {3, 6}};
		System.out.println(minMeetingRooms(meetings3));
		int[][] meetings4 = new int[][]{{1, 2}, {3, 4}, {5, 6}};
		System.out.println(minMeetingRooms(meetings4));
		int[][] meetings5 = new int[][]{{1, 3}, {2, 3}, {3, 4}};
		System.out.println(minMeetingRooms(meetings5));
		int[][] meetings6 = new int[][]{{1, 10}, {2, 3}, {4, 5}, {6, 7}};
		System.out.println(minMeetingRooms(meetings6));
	}
}
