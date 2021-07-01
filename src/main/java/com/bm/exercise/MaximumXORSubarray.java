package com.bm.exercise;

import java.util.Arrays;
import java.util.Scanner;

class Main
{
    static class TrieNode
    {
        TrieNode one;
        TrieNode zero;
        int value;
    }

    TrieNode root;

    Main()
    {
        root = new TrieNode();
    }
    public  void insert(TrieNode head,int n)
    {
        for (int i = 31; i >=0 ; i--) {
            int curr=(n>>i) &1;
//            System.out.println(curr+"((((((((((((((");
            if(curr== 1)
            {
                if(head.one ==null)
                    head.one = new TrieNode();
                head = head.one;
            }
            else
            {
                if(head.zero==null)
                    head.zero = new TrieNode();
                head= head.zero;
            }
        }
        head.value= n;
    }
    public  int query(TrieNode head,int n)
    {
        //look for opposite
        for (int i = 31; i >=0 ; i--) {
            int curr = (n>>i) &1;
            if(curr==1)
            {
                if(head.zero !=null)
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
                if(head.one !=null)
                {
                    head = head.one;
                }
                else
                {

                    head = head.zero;
                }
            }
        }
        System.out.println(head.value+"head.value");
        System.out.println(n+"nnnn");
        return  head.value ^ n;
    }
    public  int compute(int [] arr)
    {
        int maxXor = 0;

        insert(this.root,0);
        int xor =0;
        for (int i = 0; i < arr.length; i++) {
            xor= xor ^ arr[i];
            insert(this.root,xor);
            System.out.println("xor"+xor);
            System.out.println(query(this.root,xor));
            maxXor = Math.max(maxXor,query(this.root,xor));
        }
        return  maxXor;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
        Main main = new Main();
        int compute = main.compute(arr);
        System.out.println(compute);
        sc.close();
    }
}
