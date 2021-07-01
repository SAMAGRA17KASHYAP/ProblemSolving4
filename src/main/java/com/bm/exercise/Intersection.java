package com.bm.exercise;

import java.util.*;

public class Intersection
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] arr1 = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] arr2 = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(Arrays.toString(intersect(arr1,arr2)));
        sc.close();
    }
    public static   int[] intersect(int[] arr1,int[] arr2)
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            map.put(arr1[i],map.getOrDefault(arr1[i],0)+1);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++) {
            if(map.containsKey(arr2[i]))
            {
                res.add(arr2[i]);
                map.put(arr2[i],map.get(arr2[i])-1);
                if(map.get(arr2[i])==0)
                {
                    map.remove(arr2[i]);
                }
            }
        }
        return  res.stream().sorted().mapToInt(idx->idx).toArray();
    }
}
