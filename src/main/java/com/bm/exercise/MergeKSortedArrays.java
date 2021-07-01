package com.bm.exercise;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeKSortedArrays
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < nt; i++) {
            List<Integer> result = new ArrayList<>();
            int[] arr = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int n = arr[0];
            int k = arr[1];
            int[] nums=Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            List<Integer> list =new ArrayList<>();
            HashMap<Integer,Integer> map = new HashMap<>();
            int[] res =new int[k+1];
            for (int j = 0; j < nums.length; j++) {
                map.put(nums[j],map.getOrDefault(nums[j],0)+1);
                res[k]= nums[j];
                int idx=find(res,nums[j]);
                for (int t = idx-1; t >=0 ; t--) {
                  if(map.getOrDefault(res[t],0)< map.getOrDefault(res[t+1],0))
                  {
                      int temp= res[t];
                      res[t] =res[t+1];
                      res[t+1] = temp;
                  }
                  else if(map.getOrDefault(res[t],0)== map.getOrDefault(res[t+1],0)&& res[t]>res[t+1])
                  {
                      int temp= res[t];
                      res[t] =res[t+1];
                      res[t+1] = temp;
                  }
                  else
                      break;
                }
                for (int l = 0; l < k && map.get(res[l])!=null; l++) {
                    System.out.print(res[l]+" ");
                }
            }
            System.out.println();
        }
        sc.close();
    }
    public  static  int find(int[] arr,int target)
    {
        for(int i=0;i< arr.length;i++)
        {
            if(target== arr[i])
                return  i;
        }
        return -1;
    }
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr=Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int K = arr[0];
        int N = arr[1];

        List<Long> list = Arrays.stream(sc.nextLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(list.size());
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparing(Pair::getVal));
        List<Long> result = new ArrayList<>(list.size());
//        for (int i = 0; i < K; i++) {
//            queue.offer(new Pair(sublist.get(i).get(0),i,0));
//        }
//        while (!queue.isEmpty()) {
//            Pair current = queue.poll();
//            result.add(current.val);
//            if (current.idx +1 < sublist.get(current.listIdx).size())
//            {
//                queue.offer(
//                        new Pair
//                                (
//                                sublist.get(current.listIdx).get(current.idx+1),
//                                current.listIdx,
//                                current.idx+1
//                                )
//                );
//            }
//        }
        String collect = result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(collect);
    }
    static class Pair
    {
        long val;
        int listIdx;
        int idx;
        public Pair(long val, int listIdx,int idx) {
            this.val = val;
            this.listIdx = listIdx;
            this.idx=idx;
        }

        public long getVal() {
            return val;
        }
    }
}

