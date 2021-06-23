package com.ll;

import java.util.HashMap;
import java.util.Map;

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
            this.isTerminal = isTerminal;
            this.children = new HashMap<>();
        }
    }

    private  int numOfWords=0;
    private  Node root;

    Trie()
    {
        this.root = new Node('\0',false);
    }

    public  void addWord(String word)
    {
        addWord(root,word.toCharArray(),0);
    }

    private void addWord(Node parent, char[] arr, int pos)
    {
        if(pos == arr.length) {
            if (parent.isTerminal) {
                return;
            } else {
                parent.isTerminal = true;
                numOfWords++;
            }
            return;
        }
            Node child = parent.children.get(arr[pos]);
            if(child == null)
            {
                child  = new Node(arr[pos],false);
                parent.children.put(arr[pos],child);
            }
            addWord(child,arr,pos+1);

    }

    public  void display()
    {
        display(root,"");
    }

    private void display(Node parent,String word)
    {
        if(parent.isTerminal)
        {
            System.out.println(word);
        }
        for(Map.Entry<Character,Node> entry:parent.children.entrySet())
        {
            display(entry.getValue(),word+entry.getKey());
        }
    }

    public  boolean search(String target)
    {
        return  search(root,target.toCharArray(),0);
    }

    private boolean search(Node parent, char[] arr,int pos)
    {
        if(pos== arr.length)
        {
            return  parent.isTerminal;
        }
        Node node = parent.children.get(arr[pos]);
        if(node == null)
        {
            return  false;
        }
        return search(node,arr,pos+1);
    }

    public void getAllPrefix(String prefix)
    {
        getAllPrefix(root,prefix.toCharArray(),0);
    }

    private void getAllPrefix(Node parent, char[] arr, int pos)
    {
        if(pos== arr.length)
        {
            display(parent,new String(arr));
            return;
        }
        Node child =parent.children.get(arr[pos]);
        if(child == null)
        {
            return;
        }
        getAllPrefix(child,arr,pos+1);
    }

    public  boolean remove(String word)
    {
        return  remove(root,word.toCharArray(),0);
    }

    private boolean remove(Node parent, char[] arr, int pos)
    {
        if(pos == arr.length)
        {
            if(parent.isTerminal)
            {
                this.numOfWords--;
                if(parent.children.size()==0)
                    return  true;
                else
                {
                    parent.isTerminal = false;
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        Node child = parent.children.get(arr[pos]);
        if(child == null)
        {
            return  false;
        }
        boolean result = remove(child,arr,pos+1);
        if(result)
        {
            parent.children.remove(arr[pos]);

        }
        if(parent.isTerminal || parent.children.size()!=0)
            return false;
        else
            return true;

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
        System.out.println(trie.search("art"));
        System.out.println(trie.search("artist"));
        trie.getAllPrefix("ar");
        System.out.println("=============");
        trie.getAllPrefix("b");
        System.out.println("==========remove======");
        trie.remove("arts");
        trie.remove("sea");
        System.out.println("========================");
        trie.display();
    }

}
