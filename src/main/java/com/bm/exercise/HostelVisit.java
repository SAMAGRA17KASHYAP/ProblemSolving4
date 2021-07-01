package com.bm.exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HostelVisit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr=Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int nq = arr[0];
        int k = arr[1];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.comparing(Integer::intValue).reversed());
        for (int i = 0; i < nq; i++) {
            arr= Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int type= arr[0];
            if(type==1) {
                int x = arr[1];
                int y = arr[2];
                int dist = (int) (Math.pow(x, 2) + Math.pow(y, 2));
                if (pq.size() == k) {
                    if (pq.peek() > dist) {
                        pq.poll();
                        pq.offer(dist);
                    }
                } else {
                    pq.offer(dist);
                }
            }
            else
            {
                System.out.println(pq.peek());
            }
        }

        sc.close();
    }
}
