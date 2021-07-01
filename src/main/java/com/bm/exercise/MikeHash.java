package com.bm.exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class MikeHash {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        HashMap<Integer, Integer> compute = compute(arr);
        compute.entrySet().stream().sorted(Comparator.comparing(idx->idx.getValue()))
        .forEach(idx-> System.out.println(idx.getKey()));
        sc.close();
    }
    public  static  HashMap<Integer,Integer> compute(int[] arr)
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        int count =0;
        for (int i = 0; i < arr.length; i++) {
//            if(map.containsKey(arr[i]))
//            {
////                for (int j = 0; j < i; j++) {
////                    if(map.get(arr[j])> map.get(arr[i]))
////                    {
////                        map.put(arr[j],map.get(arr[j])-1);
////                    }
////                }
//                map.put(arr[i],map.size()-1);
//            }
//            else
            {
                map.put(arr[i],i);
            }
        }
        return map;
    }
}
