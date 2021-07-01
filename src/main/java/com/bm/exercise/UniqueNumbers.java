package com.bm.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class UniqueNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        int[] arr = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(compute(arr));
    }
    public  static  int compute(int[] arr)
    {
        int one=0,two=0,commonBits =0;
        for (int i = 0; i < arr.length; i++) {
            two = two|(one&arr[i]);
            one = one ^ arr[i];
            commonBits = ~(one & two);
            one =  commonBits & one;
            two = commonBits & two;
        }
        return  one;
    }
}
