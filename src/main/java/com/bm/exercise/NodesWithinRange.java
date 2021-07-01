package com.bm.exercise;

import java.sql.Array;
import java.util.*;

public class NodesWithinRange {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ntc = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < ntc; i++) {
            int ne = Integer.parseInt(sc.nextLine());
            int[] arr = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Node tree = construct(arr, Integer.MAX_VALUE,new int[]{0 });
            int[] range =Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            List<Integer> range1 = findRange(tree, new ArrayList<>(), range[0], range[1], Integer.MIN_VALUE, Integer.MAX_VALUE);
            StringJoiner sj = new StringJoiner(" ");
            for (int r:range1)
            {
                sj.add(String.valueOf(r));
            }
            System.out.println(sj);
        }
        sc.close();
    }
    public  static List<Integer> findRange(Node node,List<Integer> list,int lower,int upper,int treeLower,int treeUpper)
    {
        if(node == null)
        {
            return  list;
        }
        if(lower<= node.val && node.val<= upper)
        {
            list.add(node.val);
        }
        int leftLower = treeLower;
        int leftUpper = node.val;
        int rightlower = node.val;
        int rightUpper = treeUpper;
        if((leftLower< lower && lower < leftUpper) ||(leftLower< upper && upper < leftUpper))
        {
            findRange(node.left,list,lower,upper,leftLower,leftUpper);
        }
        if((rightlower< upper && upper< rightUpper)||(rightlower< lower && lower< rightUpper))
        {
            findRange(node.right,list,lower,upper,rightlower,rightUpper);
        }
        return list;
    }
    public static Node construct(int[] arr, int bound, int[] i)
    {
        if(i[0]>= arr.length|| arr[i[0]]> bound)
        {
            return   null;
        }
        Node node = new Node(arr[i[0]++]);
        node.left= construct(arr,node.val,i);
        node.right = construct(arr,bound,i);
        return  node;
    }
}
