package com.ll.pq;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class HeapGeneroc<T extends Comparable<T>>  {


    List<T> list = new ArrayList<>();
    public  int getLeft(int idx)
    {
        return  idx*2+1;
    }
    public  int getRight(int idx)
    {
        return  idx*2+2;
    }
    public  int parent(int idx)
    {
        return  (idx-1)/2;
    }


    public  void add(T value)
    {
        list.add(value);
        heapify(list.size()-1);
    }

    public  void heapify(int ci)
    {
        if(ci==0)
        {
            return;
        }
        int parent = parent(ci);
//        int left = getLeft(parent);
//        int right = getRight(parent);
        int minIdx = ci;
        if(list.get(parent).compareTo(list.get(ci)) > 0)
        {
            minIdx=parent;
        }
        if(minIdx != ci)
        {
            T ciVal = list.get(ci);
            T parentVal = list.get(parent);
            list.set(ci,parentVal);
            list.set(parent,ciVal);
            heapify(parent);
        }
    }

    public String toString()
    {
        return list.toString();
    }
    public  int size()
    {
        return  list.size();
    }
    public  boolean isEmpty()
    {
        return  size()==0;
    }

    public T remove()
    {
        if(isEmpty())
            throw  new RuntimeException("Heap Empty");
        T result = list.remove(0);
        if(list.isEmpty())
        {
            return  result;
        }
        T lastElement = list.remove(list.size() - 1);
        list.add(0,lastElement);
        System.out.println(list);
        heapifyDown(0);
        return  result;
    }

    private void heapifyDown(int idx)
    {
        int leftIdx = getLeft(idx);
        int rightIdx = getRight(idx);
        int minIdx= idx;

        if(leftIdx<list.size() && list.get(leftIdx).compareTo(list.get(minIdx))<0)
        {
            minIdx=leftIdx;
        }
        if(rightIdx<list.size() && list.get(rightIdx).compareTo( list.get(minIdx)) <0)
        {
            minIdx = rightIdx;
        }
        if(minIdx != idx)
        {
            T idxVal = list.get(idx);
            T minIdxVal = list.get(minIdx);
            list.set(idx,minIdxVal);
            list.set(minIdx,idxVal);
            heapifyDown(minIdx);
        }
    }

        public static void main(String[] args)
    {
        HeapGeneroc<String> hp = new HeapGeneroc<>();
        hp.add("this");
        hp.add("is");
        hp.add("not");
        hp.add("good");
        hp.add("pretty");
        System.out.println(hp);
        while (!hp.isEmpty()) {
            System.out.println(hp.remove());
        }
    }
}
