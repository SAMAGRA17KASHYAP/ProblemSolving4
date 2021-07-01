package com.ll.v1;

import com.ll.LinkedList;

import java.util.*;
import java.util.stream.Collectors;

public class HashTable<K,V> {
    class HTPair
    {
        K key;
        V value;

        public HTPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if(o== null || o.getClass() != this.getClass())
            {
                return  false;
            }
            HTPair htPair = (HTPair) o;
            return  this.key == htPair.key|| Objects.equals(this.key,htPair.key);
        }
        public  String toString()
        {
            return "{"+key+", "+value+"}";
        }
    }
     private LinkedList<HTPair>[] buckets ;
     private static final int DEFAULT_CAPACITY = 20;
    private  int capacity;
    private  int size=0;
    public static final double loadFactor = 0.75;
    public HashTable() {
        this(DEFAULT_CAPACITY);
    }
    public HashTable(int capacity)
    {
        this.capacity = capacity;
        this.buckets =(LinkedList<HTPair>[]) new LinkedList[this.capacity];
    }
    public  HTPair get(K key)
    {
        int bl = key.hashCode() % capacity;
        LinkedList<HTPair> list = buckets[bl];
        if(list== null)
        {
            return null;
        }
        int idx = list.find(new HTPair(key, null));
        if(idx != -1)
            return list.getNodeAt(idx).getData();
        return null;
    }
    public  void put(K key,V value)
    {
        HTPair htPair = new HTPair(key,value);
        int bl = key.hashCode() % capacity;
        LinkedList<HTPair>  list = buckets[bl];
        if(list== null)
        {
            buckets[bl] = new LinkedList<>();
            list = buckets[bl];
        }
        int idx = list.find(htPair);
        if(idx== -1)
        {
            list.addLast(htPair);
        }
        else
        {
            HTPair node = list.getNodeAt(idx).getData();
            node.value = value;
        }
        size++;
        double lf = ((double) this.size)/this.capacity;
        if(lf> HashTable.loadFactor)
        {
            rehash();
        }
    }
    public  void rehash()
    {
        int nCapcaity = this.capacity * 2;
        LinkedList<HTPair>[] nBucket =(LinkedList<HTPair>[]) new LinkedList[nCapcaity];
        LinkedList<HTPair>[] oldBucket = buckets;
        this.buckets = nBucket;
        this.capacity = nCapcaity;
        this.size=0;
        for (LinkedList<HTPair> item: oldBucket)
        {
            if(item != null)
            {
                while(!item.isEmpty())
                {
                    HTPair htPair = item.removeFirst();
                    put(htPair.key, htPair.value);
                }
            }
        }
    }
    public  void display()
    {
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("Visiting "+ i+ "th bucket");
            System.out.println(buckets[i]);
        }
    }

    public static void main(String[] args) {
        HashTable<String,Integer> pop = new HashTable<>();
        pop.put("IND",123);
        pop.put("IND1",12322);
        pop.put("IND2",1232);
        pop.put("IND",1231);
        pop.display();
        System.out.println(pop.get("IND1"));
    }
    static class Solution {
         static  class  Triplet
        {
            int a,b,c;

            public Triplet(int a, int b, int c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }
            public static   Triplet max(Triplet t1,Triplet t2)
            {
                int a= Math.max(t1.a,t2.a);
                int b= Math.max(t1.b,t2.b);
                int c= Math.max(t1.c,t2.c);
                return  new Triplet(a,b,c);
            }
        }
        public boolean mergeTriplets(int[][] triplets, int[] target) {
            java.util.LinkedList<Triplet> list = new java.util.LinkedList<>();
            for(int i=0;i< triplets.length;i++)
            {
                if(triplets[i][0] == target[0] && triplets[i][1]== target[1] && triplets[i][2]== target[2])
                {
                    return  true;
                }
                if(triplets[i][0] <= target[0] && triplets[i][1]<= target[1] && triplets[i][2]<= target[2])
                {
                    list.add(new Triplet(triplets[i][0],triplets[i][1],triplets[i][2]));
                }

            }
            if(list.isEmpty())
            {
                return  false;
            }
            while (list.size()!=1)
            {
                Triplet t1 = list.removeFirst();
                Triplet t2 = list.removeFirst();
                list.add( Triplet.max(t1,t2));
            }
            Triplet t1 = list.removeFirst();
            return  t1.a == target[0] && t1.b== target[1] && t1.c== target[2];
        }
    }
}
