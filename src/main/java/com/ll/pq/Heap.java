package com.ll.pq;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Heap {

    List<Integer> list = new ArrayList<>();
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


    public  void add(int value)
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
        int left = getLeft(parent);
        int right = getRight(parent);
        int minIdx = ci;
        if(list.get(parent) > list.get(ci))
        {
            minIdx=parent;
        }
        if(minIdx != ci)
        {
            int ciVal = list.get(ci);
            int parentVal = list.get(parent);
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

    public int remove()
    {
        if(isEmpty())
            throw  new RuntimeException("Heap Empty");
        Integer result = list.remove(0);
        if(list.isEmpty())
        {
            return  result;
        }
        Integer lastElement = list.remove(list.size() - 1);
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

        if(leftIdx<list.size() && list.get(leftIdx)<list.get(minIdx))
        {
            minIdx=leftIdx;
        }
        if(rightIdx<list.size() && list.get(rightIdx) < list.get(minIdx))
        {
            minIdx = rightIdx;
        }
        if(minIdx != idx)
        {
            int idxVal = list.get(idx);
            int minIdxVal = list.get(minIdx);
            list.set(idx,minIdxVal);
            list.set(minIdx,idxVal);
            heapifyDown(minIdx);
        }
    }

    public static void main(String[] args)
    {
        Heap hp = new Heap();
        Random random = new Random();
        random.ints(20,100)
                .limit(7)
                .peek(System.out::println)
                .forEach(x-> hp.add(x));
        System.out.println("==================");
        System.out.println(hp);
        System.out.println("=====remove=======");

        while (!hp.isEmpty()) {
            System.out.println(hp.remove());
        }
    }
}
