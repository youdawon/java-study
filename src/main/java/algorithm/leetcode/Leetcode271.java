/*
Design an algorithm to encode a list of strings to a string.
The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Example
Example1

Input: ["lint","code","love","you"]
Output: ["lint","code","love","you"]
Explanation:
One possible encode method is: "lint:;code:;love:;you"
Example2

Input: ["we", "say", ":", "yes"]
Output: ["we", "say", ":", "yes"]
Explanation:
One possible encode method is: "we:;say:;:::;yes"

Note:
The string may contain any possible characters out of 256 valid ascii characters.
Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states.
Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods.
You should implement your own encode/decode algorithm.
*/

package algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode271 {

	public static String encode(List<String> list){
		StringBuilder sb = new StringBuilder();
		for(String str : list){
			sb.append(str.length() + "#" + str);
		}

		return sb.toString();
	}

	public static String[] decode(String s){

		List<String> result = new ArrayList<>();

		int i = 0;
		while(i < s.length()){
			int j = i;
			while(s.charAt(j) != '#'){
				j++;
			}
			int size = Integer.parseInt(s.substring(i, j));
			result.add(s.substring(j + 1, j + 1 + size));
			i = j + size + 1;
		}


		return result.toArray(new String[result.size()]);
 	}

	public static void main(String[] args){

		List<String> list = new ArrayList<>();
		list.add("testtesttesttest");
		list.add("code");
		list.add("love");
		list.add("you");

		System.out.println(encode(list));
		String[] result = decode(encode(list));

		for(String str : result) {
			System.out.println(str);
		}
	}
}
