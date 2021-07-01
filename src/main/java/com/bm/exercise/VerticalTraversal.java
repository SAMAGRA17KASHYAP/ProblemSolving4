package com.bm.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalTraversal
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Node construct = construct(arr);
        TreeMap<Integer,List<Integer>> res=new TreeMap<>();
        traverse(construct,res,0);

        for (List<Integer>temp:res.values()) {
            String collect = temp.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(collect);
        }
    }

    private static void traverse(Node construct, TreeMap<Integer, List<Integer>> res,int level) {
        if(construct!=null)
        {
            if(res.get(level)==null)
            {
                res.put(level,new ArrayList<>());
            }
            res.get(level).add(construct.data);
            traverse(construct.left,res,level-1);
            traverse(construct.right,res,level+1);
        }
    }

    public  static  Node construct(int[] arr)
    {
        if(arr== null|| arr.length==0)
            return null;
        int idx =0;
        Node node = new Node(arr[idx]);
        idx++;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty())
        {
            Node current = queue.poll();
            if(arr[idx]!=-1)
            {
                Node nd = new Node(arr[idx]);
                current.left = nd;
                queue.offer(current.left);
            }
            idx++;
            if(arr[idx]!=-1)
            {
                Node nd = new Node(arr[idx]);
                current.right =nd;
                queue.offer(current.right);
            }
            idx++;
        }
        return  node;
    }
    static  class Node
    {
        int data;
        Node left;
        Node right;
        Node(int data)
        {
            this.data = data;
        }
    }
}
