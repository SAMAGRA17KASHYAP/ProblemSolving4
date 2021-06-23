package com.ll;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class HashTable<K,V>
{
    class HTPair
    {
        K key;
        V value;

        HTPair(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HTPair htPair = (HTPair) o;
            return Objects.equals(key, htPair.key) ;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    int size = 0;
    public  static final int DEFAULT_CAPACITY= 10;
    LinkedList<HTPair> [] bucketArr;

    public  int tableSize;
    HashTable()
    {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int defaultCapacity)
    {
        bucketArr= (LinkedList<HTPair> []) new LinkedList[defaultCapacity];
        tableSize = defaultCapacity;
    }

    public void put(K key, V value)
    {
        int bucketLocation= key.hashCode()%tableSize;
        if( bucketArr[bucketLocation] == null)
        {
            bucketArr[bucketLocation] = new LinkedList<>();
        }
        HTPair htPair = new HTPair(key, value);
        int loc = bucketArr[bucketLocation].find(htPair);
        if(loc == -1)
        {
            bucketArr[bucketLocation].addFirst(htPair);
            this.size++;
        }
        else
        {
            LinkedList<HTPair>.Node nodeAt = bucketArr[bucketLocation].getNodeAt(loc);
            nodeAt.data.value = value;
        }

        double lambda = this.size*1.0/this.tableSize;
        if(lambda>0.75)
            this.rehash();
    }

    public V get(K key)
    {
        int bucketLocation = key.hashCode() % tableSize;
        if(bucketArr[bucketLocation]==null)
        {
            return null;
        }
        else
        {
            HTPair htPair = new HTPair(key, null);
            int idx = bucketArr[bucketLocation].find(htPair);
            if(idx!= -1)
            {
                return  bucketArr[bucketLocation].getNodeAt(idx).data.value;
            }
            else
            {
                return null;
            }
        }
    }

    public  HTPair remove(K key)
    {
        int bucketLocation = key.hashCode()% tableSize;
        if(null==bucketArr[bucketLocation])
        {
            return null;
        }
        LinkedList<HTPair> list = bucketArr[bucketLocation];
        HTPair htp = new HTPair(key,null);
        int idx = list.find(htp);
        if(idx == -1)
        {
            return null;
        }
        return list.getNodeAt(idx).data;
    }

    public  void rehash()
    {
        System.out.println("rehash");
        System.out.println("size"+ size);
        System.out.println("tablesize"+tableSize);
        LinkedList<HTPair>[] obA = this.bucketArr;
        tableSize = 2* tableSize;
        this.bucketArr = new LinkedList[tableSize];

        this.size=0;
        for(LinkedList<HTPair> obI:obA)
        {
            while(obI != null && !obI.isEmpty())
            {
                HTPair htPair = obI.removeFirst();
                this.put(htPair.key, htPair.value);
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String collect = Arrays.stream(bucketArr)
                .filter(x -> x != null)
                .map(x->x.toString())
                .collect(Collectors.joining("\n---------------------------\n","\n**********\n","\n***********\n"));
        return "HashTable{" +
                "size=" + size +
                ", bucketArr=" + collect +
                ", tableSize=" + tableSize +
                '}';
    }

    public static void main(String[] args) {
        HashTable<String,Integer> ht = new HashTable(2);
        ht.put("IND",10);
        ht.put("IND1",410);
        ht.put("IND2",510);
        ht.put("IND3",40);
        System.out.println(ht);
        System.out.println(ht.get("IND"));

        System.out.println(ht.get("ID31"));

    }
}
