package com.bm.exercise;

import java.util.*;

public class MakeAllSubsequence
{
    public  static List<String> compute(String str,int idx,HashSet<Character> set)
    {
        if(idx == str.length())
        {
            ArrayList<String> result = new ArrayList<>();
            result.add("");
            return  result;
        }
        List<String> result = compute(str,idx+1,set);
        if(!set.contains(str.charAt(idx))) {
            List<String> lR = new ArrayList<>();

            for (String st : result) {
                lR.add(str.charAt(idx) + st);
            }
            result.addAll(lR);
        }
        return  result;
    }

    public  static  int countAllSubs(String str)
    {
        int[] dp = new int[str.length()+1];
        int[] chars = new int[255];
        dp[0]=1;
        Arrays.fill(chars,-1);
        for (int i = 1; i <= str.length() ; i++) {
            dp[i] = 2*dp[i-1];
            if(chars[str.charAt(i-1)]!= -1)
            {
                dp[i] = dp[i] -dp[chars[str.charAt(i-1)]];
            }
            chars[str.charAt(i-1)]= i-1;
        }
        return  dp[str.length()];
    }

    public  static  int countAllSubsWithoutExtraSpace(String str)
    {
        int count=1;
        int[] chars = new int[255];
        Arrays.fill(chars,-1);
        for (int i = 1; i <= str.length() ; i++) {
            int prev = count;
            count = 2*count;
            if(chars[str.charAt(i-1)]!= -1)
            {
                count = count -chars[str.charAt(i-1)];
            }
            chars[str.charAt(i-1)]= prev;
        }
        return  count;
    }

    public static void main(String[] args) {
        int n=10;
        for (int i = 0; i <=n ; i++) {
            System.out.print(i+"\t");
        }
        System.out.println();
        for (int i = 1; i < n ; i++) {
            System.out.print(i+"\t");
            for (int j = 1; j < 10; j++) {
                System.out.print((i^j)+"\t");
            }
            System.out.println();
        }
    }
    public static void main2(String[] args) {
        System.out.println(countAllSubs("abcd"));
        System.out.println(countAllSubs("abaa"));
        System.out.println(countAllSubsWithoutExtraSpace("abcd"));
        System.out.println(countAllSubsWithoutExtraSpace("abaa"));
    }
    public static void main1(String[] args) {
        String input= "abcd";
        System.out.println( compute(input,0,new HashSet<>()));
        System.out.println( compute(input,0,new HashSet<>()).size());
        System.out.println( new HashSet<>(compute(input,0,new HashSet<>())).size());
        input= "aabc";
        System.out.println( compute(input,0,new HashSet<>()));
        System.out.println( compute(input,0,new HashSet<>()).size());
        System.out.println( new HashSet<>(compute(input,0,new HashSet<>())).size());
    }
}