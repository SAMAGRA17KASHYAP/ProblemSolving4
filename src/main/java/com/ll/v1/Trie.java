package com.ll.v1;

import java.util.HashMap;

public class Trie
{
    class Node
    {
        char data;
        HashMap<Character,Node> children;
        boolean isTerminal;

        Node(char data, boolean isTerminal)
        {
            this.data = data;
            this.children = new HashMap<>();
            this.isTerminal = isTerminal;
        }
    }

    private  int numOfWords =0;
    private  Node root;

    Trie()
    {
        this.root = new Node('\0',false);
    }

    public  void  addWord(String word)
    {
        addWord(root,word.toCharArray(),0);
    }


    private  void  addWord(Node node,char[] arr,int idx)
    {
        if(idx == arr.length)
        {
            node.isTerminal = true;
            return;
        }
        Node child = node.children.get(arr[idx]);
        if(child == null)
        {
            child = new Node(arr[idx],false);
            node.children.put(arr[idx],child);
        }
        addWord(child,arr,idx+1);
    }

    public  void display()
    {
        display(root,"");
    }
    private  void  display(Node node, String dtd)
    {
        if(node == null)
        {
            return;
        }
        if(node.isTerminal)
        {
            System.out.println(dtd);
        }
        node.children.entrySet()
                .forEach(item-> display(item.getValue(),dtd+item.getKey()));

    }

    public  boolean  search(String word)
    {
        return  search(root,word.toCharArray(),0);
    }
    private  boolean search(Node node,char[] arr,int idx)
    {
        if(idx == arr.length)
        {
            return  node.isTerminal;
        }
        Node child = node.children.get(arr[idx]);
        if(child != null )
        {
            return  search(child,arr,idx+1);
        }
        else
        {
            return  false;
        }
    }

    public  void  remove(String word)
    {
        remove(root,word.toCharArray(),0);
    }

    private  boolean remove(Node node, char[] arr,int idx)
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
                if(node.children.size() == 0)
                {
                    return  true;
                }
                else
                {
                    return  false;
                }
            }
        }
        Node child = node.children.get(arr[idx]);

        if(child == null)
        {
            return  false;
        }
        boolean result = remove(child,arr,idx+1);
        if(result)
        {
            node.children.remove(arr[idx]);
            if( node.children.size()== 0)
           {
               return  result;
           }
           else
           {
               return  false;
           }
        }
        return  result;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addWord("art");
        trie.addWord("arts");
        trie.addWord("boy");
        trie.addWord("buy");
        trie.addWord("bought");
        trie.addWord("seen");
        trie.addWord("sea");
        trie.display();
        System.out.println("========");
        System.out.println(trie.search("buy"));
        System.out.println(trie.search("buywee"));
        System.out.println("========");
        trie.remove("art");
        trie.display();
        System.out.println();
    }
}
