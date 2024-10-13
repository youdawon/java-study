package algorithm.dp;

import java.util.List;

public class wildcard {

  public static boolean isMatching(String target, String wildcard){

    int i=0;
    while(i < target.length() && i < wildcard.length() &&
        (wildcard.charAt(i) == '?' || target.charAt(i) == wildcard.charAt(i))){
      i++;
    }

    if(i == wildcard.length()){
      return target.length() == wildcard.length();
    }

    int j = i;

    if(wildcard.charAt(i) != '*'){
      i++;
    }

    i++;

    while(j < target.length()){
      if(wildcard.charAt(i) == target.charAt(j)) {
        return true;
      }
      j++;
    }

    return false;
  }


  public static void main(String[] args){

    List<String> list = List.of("help", "papa", " hello");

    for(String str : list){
      System.out.println(isMatching(str, "he?p"));
    }
  }
}


//  int m = target.length();
//  int n = wildcard.length();
//  boolean[][] dp = new boolean[m+1][n+1];
//    dp[0][0] = true;
//
//        for(int i=1; i<=m; i++){
//        for(int j=1; j<=n; j++){
//        if(wildcard.charAt(j-1) == '?'){
//        dp[i][j] = dp[i-1][j-1];
//        } else if(wildcard.charAt(j-1) == '*'){
//        dp[i][j] = dp[i][j-1];
//        } else {
//        dp[i][j] = dp[i-1][j-1] && target.charAt(i-1) == wildcard.charAt(j-1);
//        }
//        }
//        }
//
//        return dp[m][n];