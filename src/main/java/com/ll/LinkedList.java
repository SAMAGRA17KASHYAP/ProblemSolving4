package com.ll;

public class LinkedList<T>
{
    public  class Node
    {
        T data;
        Node next;
        Node(T data,Node next)
        {
            this.data = data;
            this.next = next;
        }
        public  T getData()
        {
            return data;
        }
    }
    Node head;
    Node tail;
    int size=0;

    public boolean isEmpty()
    {
        return  size == 0;
    }

    public  void addFirst(T item)
    {
        Node newNode = new Node(item, head);
        head = newNode;
        if(isEmpty())
        {
            tail = newNode;
        }
        size++;
    }
    public  void addLast(T item)
    {
        Node newNode = new Node(item,null);
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
            {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void  addAt(T item, int idx)
    {
        if(idx<0 || idx > size)
        {
            throw  new RuntimeException("idx out of bound.");
        }
        if(idx== 0)
        {
            addFirst(item);
            size++;
        }
        else if(idx == size)
        {
            addLast(item);
            size++;
        }
        else
        {
            Node temp = head;
            for (int i = 1; i <idx ; i++) {
                temp = temp.next;
            }

            Node newNode = new Node(item, temp.next);
            temp.next = newNode;
            size++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[HEAD] ");
        Node temp = head;
        if(temp == null)
        {
            sb.append("->");
        }
        while ( temp != null)
        {
            sb.append(" -> ");
            sb.append(temp.data);
            temp = temp.next;
        }
        sb.append(" <- [TAIL]");
        return sb.toString();
    }

    public  T removeFirst()
    {
        if(isEmpty())
        {
            throw  new RuntimeException("List is empty");
        }
        size--;
        Node temp = head;
        head = head.next;
        if(isEmpty())
        {
            tail = null;
        }
        return  temp.data;
    }

    public T removeLast()
    {
        if(isEmpty())
        {
            throw  new RuntimeException("List is empty");
        }
        size--;
        Node item = tail;
        if(isEmpty())
        {
            head= null;
            tail = null;
        }
        else
        {
           tail= getNodeAt(size-1);
           tail.next=null;
        }
        return  item.data;
    }
    public  Node getNodeAt( int idx)
    {
        if(idx>= size|| head == null)
        {
            throw new RuntimeException("Idx is incorect");
        }
        Node temp = head;
        for(int i=0;i<idx;i++)
        {
            temp = temp.next;
        }
        return  temp;

    }

    public  T removeAt(int idx)
    {
        if(idx>=size || idx<-1)
        {
            throw  new RuntimeException("idx is not in range");
        }

        if(idx == 0)
        {
            return  removeFirst();
        }
        else  if (idx == size-1)
        {
            return removeLast();
        }
        else
        {
            Node nM1=getNodeAt(idx-1);
            Node temp = nM1.next;
            nM1.next = temp.next;
            size--;
            return temp.data;
        }

    }

    public int find(T data){
        int index = 0;
        Node temp = head;
        for(;temp!= null;temp = temp.next)
        {
            if(temp.data.equals(data))
            {
                return  index;
            }
            index++;
        }
        return  -1;
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("one");
        list.addFirst("two");
        list.addFirst("three");
        System.out.println(list);
        System.out.println("===================================");
        list.addLast("ten");
        list.addLast("eleven");
        System.out.println(list);
        list.addAt("Added",2);
        System.out.println("==================================");
        System.out.println(list);
        System.out.println(list.size);
        for(int i=0;i< list.size;i++)
        {
            System.out.println(i+"========"+ list.getNodeAt(i).data);
        }
        System.out.println("==================================");

        list.removeFirst();
        list.removeLast();
        System.out.println("===========================");
        System.out.println(list);

        list.removeAt(1);
        System.out.println(list);
    }
}
