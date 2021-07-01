package com.bm.exercise;

import java.util.*;

public class BottomView {

    static class Node
    {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(item -> Integer.parseInt(item))
                .toArray();

        Node tree = construct(arr);
        TreeMap<Integer,Integer> map = new TreeMap<>();
        bottomView(tree,map,0);
        StringJoiner sj = new StringJoiner(" ");
        map.values().stream().forEach(item -> sj.add(String.valueOf(item)));
        System.out.println(sj);
    }
    public static   void bottomView(Node node,Map<Integer,Integer>map,int level)
    {
        if(node== null)
        {
            return;
        }
        map.put(level,node.val);
        bottomView(node.left,map,level-1);
        bottomView(node.right,map,level+1);
    }

    public  static Node construct(int[] arr)
    {
        if(arr== null|| arr.length==0)
        {
            return null;
        }
        int idx=0;
        Node root = new Node(arr[0]);
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        idx++;
        while (!queue.isEmpty())
        {
            Node current = queue.poll();
            if(arr[idx]!= -1)
            {
                current.left = new Node(arr[idx]);
                queue.offer(current.left);
            }
            idx++;
            if(arr[idx]!=-1)
            {
                current.right = new Node(arr[idx]);
                queue.offer(current.right);
            }
            idx++;
        }
        return  root;

    }
}
