package com.algorithms;

public class InsertSort {

	public static void main(String[] args){

		String alphabet = "BCDAEHGF";

		char[] charArr = alphabet.toCharArray();

		int length = charArr.length;

		for(int i=1; i<length; i++){

			char c = charArr[i];

			if(c < charArr[i-1]){
				char temp = charArr[i];
				int j = i;
				while(j > 0 && charArr[j-1] > temp){
					charArr[j] = charArr[j-1];
					j--;
				}
				charArr[j] = temp;
			}
		}

		System.out.println(String.valueOf(charArr));
	}
}
