package com.bm.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RangeXOR {
    static class Node
    {
        Node zero;
        Node one;
        List<Integer> indices;
        Node()
        {
            this.indices = new ArrayList<>();
        }
    }

    Node root = new Node();

    public  void insert(Node head,int value,int idx)
    {
        for (int i = 31; i >=0; i--) {
            int curr = (value >> i) & 1;
            if (curr == 1) {
                if(head.one==null)
                {
                    head.one = new Node();
                }
                head.indices.add(idx);
                head = head.one;
            } else
            {
                if(head.zero== null)
                {
                    head.zero= new Node();
                }
                head.indices.add(idx);
                head = head.zero;
            }
        }
        head.indices.add(idx);
    }

    public  boolean binarySearch(List<Integer>indices,int low,int upper)
    {
        int left= 0;
        int right = indices.size()-1;
        while(left<= right)
        {
            int mid = (left+right)/2;
            int val = indices.get(mid);
            if(low<= val && val <=upper)
            {
                return  true;
            }
            else if(val< low)
            {
                left= mid+1;
            }
            else if(val> upper)
            {
                right = mid-1;
            }
        }
        return  false;
    }

    public  int maxXor(int value,Node head,int low,int upper)
    {
        int result = 0;
        for (int i = 31; i >=0 ; i--) {
            int curr = (value >> i) & 1;
            if(curr==1)
            {
                if(head.zero !=null && binarySearch(head.zero.indices,low,upper ))
                {
                    head= head.zero;
                }
                else
                {
                    result+= Math.pow(2,i);
                    head= head.one;
                }
            }
            else
            {
                if(head.one != null && binarySearch(head.one.indices,low,upper))
                {
                    result+= Math.pow(2,i);
                    head = head.one;
                }
                else
                {
                    head = head.zero;
                }
            }
        }
        return  result;
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int q = scn.nextInt();
        RangeXOR xor = new RangeXOR();
        int elementNumber = 0;
        for (int i = 0; i < q; i++) {
            int type = scn.nextInt();
            if (type == 0) {
                int val=scn.nextInt();
                xor.insert(xor.root,val, elementNumber++);
            } else if(type==1) {
                int l=scn.nextInt();
                int r=scn.nextInt();
                int x=scn.nextInt();
                System.out.println(xor.maxXor(x,xor.root, l-1, r-1));
            }
        }
    }

}
