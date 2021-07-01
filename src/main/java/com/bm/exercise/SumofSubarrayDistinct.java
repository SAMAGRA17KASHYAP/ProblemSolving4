package com.bm.exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class SumofSubarrayDistinct {

    public  static int compute(int[] arr)
    {
        int sum=0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {

            set.add(arr[i]);
            sum+= set.size();
            for (int j =i+1; j <arr.length ; j++) {
                if(set.contains(arr[j]))
                    break;
                else
                {
                    set.add(arr[j]);
                    sum+= set.size();
                }
            }
            set.clear();
        }
        return sum;
    }

    public static  int compute2(int[] arr)
    {
        int sum = 0;
        int start=0;
        int end=0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0,j=0 ; i < arr.length ; i++) {
            start = i;
            while (j< arr.length )
            {
                if(set.contains(arr[j]))
                {
                    break;
                }
                set.add(arr[j]);
                end = j;
                j++;
            }
            sum+= (end-start+1)*(end-start+2)/2;
            set.remove(arr[i]);
        }
        return  sum;

    }

    public static void main2(String[] args) {
        Scanner sc= new Scanner(System.in);
        sc.nextLine();
        int[] arr = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(compute(arr));
        sc.close();
    }

    public static void main(String[] args) {
        System.out.println(compute2(new int[]{1,2,3}));
        System.out.println(compute2(new int[]{1,2,1}));
        System.out.println(compute2(new int[]{1,2,3,4}));
    }
}
