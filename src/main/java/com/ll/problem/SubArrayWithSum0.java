package com.ll.problem;

import java.util.HashSet;
import java.util.Set;

/***
 * https://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/
 *
 * in a prefix sum array  p[j] - p[i] = Sum[i+1,j]
 * p[j] == p[i] when Sum is zero
 * hence in prefix array we are checking whether we have some  repeatation or  not
 */
public class SubArrayWithSum0 {
    public  static boolean isSubarray(int[] arr)
    {
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++)
        {
            sum+= arr[i];
            if(sum == 0 || arr[i] ==0|| set.contains(sum))
            {
                return  true;
            }
            set.add(sum);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] input = new int[]{-3,2,3,1,6};
        //-3 -1  2 3 9
        System.out.println(isSubarray(input));
        input = new int[]{-3,0,3,1,6};
        //-3  -3 0 1 7
        System.out.println(isSubarray(input));
    }

}
