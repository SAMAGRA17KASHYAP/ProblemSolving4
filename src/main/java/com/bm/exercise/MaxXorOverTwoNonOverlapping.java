package com.bm.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class MaxXorOverTwoNonOverlapping
{
    static  class Node{
        Node zero,one;
        int value;
    }
    Node root = new Node();
    public  void insert(Node head,int value)
    {
        for (int i = 31; i >=0 ; i--)
        {
            int curr = (value >> i) & 1;
            if(curr == 1)
            {
                if(head.one== null)
                {
                    head.one = new Node();
                }
                head = head.one;
            }
            else
            {
                if(head.zero== null)
                {
                    head.zero = new Node();
                }
                head = head.zero;
            }
        }
        head.value = value;
    }
    public  int query(Node head,int value)
    {
        for (int i = 31; i >=0 ; i--) {
            int curr = (value>>i) & 1;
            if(curr == 1)
            {
                if(head.zero != null)
                {
                    head = head.zero;
                }
                else
                {
                    head = head.one;
                }
            }
            else
            {
                if(head.one != null)
                {
                    head = head.one;
                }
                else
                {
                    head = head.zero;
                }
            }
        }
        return  value ^ head.value;
    }
    public static int compute(int[] arr)
    {
        MaxXorOverTwoNonOverlapping lx = new MaxXorOverTwoNonOverlapping();
        int[] lbest = new int[arr.length];
        int xor = 0;
        lx.insert(lx.root, 0);
        for (int i = 0; i < arr.length ; i++) {
            xor =xor^arr[i];
            lx.insert(lx.root,xor);
            int res = lx.query(lx.root, xor);
            lbest[i] = i==0?0:Math.max(lbest[i-1],res);
        }
        MaxXorOverTwoNonOverlapping rx = new MaxXorOverTwoNonOverlapping();
        xor=0;
        int[] rbest = new int[arr.length];
        int maxValue =0;
        rx.insert(rx.root, 0);
        for (int i = arr.length-1;i >=0; i--) {
            xor = xor ^ arr[i];
            rx.insert(rx.root,xor);
            int res = rx.query(rx.root,xor);
            rbest[i]= arr.length-1== i ?0 : Math.max(rbest[i+1],res);
            if(i!=0)
               maxValue = Math.max(maxValue,rbest[i]+lbest[i-1]);
        }
        return  maxValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        int[] arr = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int compute = MaxXorOverTwoNonOverlapping.compute(arr);
        System.out.println(compute);
        sc.close();
    }
}
