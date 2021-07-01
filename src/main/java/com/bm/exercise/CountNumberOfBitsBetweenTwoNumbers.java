package com.bm.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class CountNumberOfBitsBetweenTwoNumbers {

    public static  int compute(int start,int end)
    {
        int count=0;
        for (int i = start; i <= end ; i++)
        {
            for (int n = i; n>0; n=n>>1) {
                    if((n&1)==1)
                    {
                        count++;
                    }
            }

        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < nt; i++) {
            int[] s = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(compute(s[0],s[1]));
        }
        sc.close();
    }
}
