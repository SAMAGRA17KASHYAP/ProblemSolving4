package com.bm.exercise;

import java.util.*;

public class TrieDictinaryProblem {

    static class Node {
        Character data;
        HashMap<Character, Node> children;
        boolean isComplete;

        public Node(Character data) {
            this.data = data;
            this.children = new HashMap<>();
            this.isComplete = false;
        }
    }

    Node root;

    TrieDictinaryProblem()
    {
        root = new Node('/');
    }

    public  void insert(String s)
    {
        insert(this.root,s,0);
    }
    private  void insert(Node parent,String s,int idx)
    {
        if(idx == s.length())
        {
            parent.isComplete = true;
            return;
        }
        Node node = parent.children.get(s.charAt(idx));
        if(node == null)
        {
            node = new Node(s.charAt(idx));
            parent.children.put(s.charAt(idx),node);
        }
        insert(node,s,idx+1);
    }

    public  void findAll(String s)
    {
        LinkedList<String> list = new LinkedList<>();
        findAll(this.root,s,0,"",list);
        list.sort(String::compareTo);
        list.forEach(System.out::println);
    }
    public  void findAll(Node parent,String s,int idx,String ans,LinkedList<String> res)
    {
        if(idx == s.length())
        {
            exploreAllChildren(parent,ans,res);
            return;
        }
        Node node = parent.children.get(s.charAt(idx));
        if(node== null)
        {
            return;
        }
        findAll(node,s,idx+1,ans+s.charAt(idx),res);
    }

    private void exploreAllChildren(Node parent, String ans, LinkedList<String> res) {

        Set<Map.Entry<Character, Node>> entries = parent.children.entrySet();
        if(parent.isComplete)
            res.add(ans);

        for(Map.Entry<Character,Node> entry: entries)
        {
            exploreAllChildren(entry.getValue(),ans+entry.getKey(),res);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ni = Integer.parseInt(sc.nextLine());
        TrieDictinaryProblem tdp = new TrieDictinaryProblem();
        for (int i = 0; i < ni; i++) {
            tdp.insert(sc.nextLine());
        }
        int nq = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < nq; i++) {
           tdp.findAll(sc.nextLine().toLowerCase());
        }
        sc.close();
    }
}



















