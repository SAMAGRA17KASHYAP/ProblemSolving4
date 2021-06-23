package com.ll.v1.v2;

import java.util.HashMap;

public class Trie {
    class  Node
    {
        char data;
        HashMap<Character,Node> children;
        boolean isTerminal;
        Node(char data, boolean isTerminal)
        {
            this.data = data;
            this.children = new HashMap<>();
            this.isTerminal = false;
        }

    }
    Node root = null;

    Trie()
    {
        this.root = new Node('\0',false);
    }
    public  void add(String word)
    {
        add(root,word.toCharArray(),0);
    }

    public  void  add(Node node, char[] arr,int idx)
    {
        if(idx == arr.length)
        {
            node.isTerminal = true;
            return;
        }
        Node child = node.children.get(arr[idx]);
        if(child == null)
        {
            child= new Node(arr[idx],false);
            node.children.put(arr[idx],child);
        }
        add(child,arr,idx+1);
    }
    public  boolean contains(String word)
    {
        return contains(root,word.toCharArray(),0);
    }
    private  boolean contains(Node node,char[] arr,int idx)
    {

        if(idx == arr.length)
        {
            return  node.isTerminal;
        }
        Node child = node.children.get(arr[idx]);
        if(child == null)
        {
            return  false;
        }
        return  contains(child,arr,idx+1);
    }
    public  void display()
    {
        display(root,"");
    }
    public  void  display(Node node,String dtd)
    {
        if(node.isTerminal)
        {
            System.out.println( dtd);
        }
        node.children.entrySet()
                .stream().forEach(item-> display(item.getValue(),dtd+item.getKey()));
    }
    public  void remove(String word)
    {
         remove(root,word.toCharArray(),0);
    }

    private  boolean remove(Node  node, char[] arr,int idx)
    {
        if(idx == arr.length)
        {
            if(!node.isTerminal)
            {
                return  false;
            }
            else
            {
                node.isTerminal = false;
                return node.children.isEmpty();
            }
        }
        Node child = node.children.get(arr[idx]);
        if(child == null)
        {
            return  false;
        }
        boolean result = remove(child,arr,idx+1);
        if(result && node.isTerminal)
        {

            node.children.remove(arr[idx]);
            return node.children.isEmpty();
        }
        else
        {
            return result;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("abc");
        trie.add("abcd");
        trie.add("bou");
        trie.add("buy");
        trie.display();
        System.out.println(trie.contains("noole"));
        System.out.println(trie.contains("bou" ));
        System.out.println("=======");
        trie.remove("abcd");
        System.out.println("-----------------");
        trie.display();
    }
}
