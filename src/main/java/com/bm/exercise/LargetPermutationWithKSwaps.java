package com.bm.exercise;

import java.util.*;
import java.util.stream.Stream;

public class LargetPermutationWithKSwaps {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n= arr[0];
        int k= arr[1];
        arr=Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] result = largePerm(arr,k);
        StringJoiner sj = new StringJoiner(" ");
        for (int r:result)
            sj.add(String.valueOf(r));
        System.out.println(sj);
        sc.close();
    }

    private static int[] largePerm(int[] arr, int k) {

        if(k==0)
            return arr;

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],i);
        }
        int[] nty = map.keySet().stream().sorted(Comparator.reverseOrder())
                .mapToInt(idx -> idx).toArray();
        int idx= 0;
//        System.out.println(map);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(nty));
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!= nty[i])
            {
                //swap i and map.get(nty)
                int temp = arr[map.get(nty[i])];
                arr[map.get(nty[i])] = arr[i];
                arr[i] = temp;
                map.put(arr[map.get(nty[i])] ,map.get(nty[i]));
                map.put(arr[i] ,i);
//                System.out.println(map);
                k--;
                if(k==0)
                    break;
            }
        }
        return arr;
    }

}
