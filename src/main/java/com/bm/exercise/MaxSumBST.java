package com.bm.exercise;

import java.util.*;

public class MaxSumBST {
//    static  class Node
//    {
//        int data;
//        Node left;
//        Node right;
//
//        public Node(int data) {
//            this.data = data;
//        }
//    }

    public static void main1(String[] args) {
        Scanner sc= new Scanner(System.in);
        sc.nextLine();
        int[] preorder = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] inorder = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).
                        toArray();

        TreeNode root = construct(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
       // preorder(root);
        System.out.println(Math.max(maxBST(root).maxnc,0));
       // System.out.println(Math.max(maxBST(root).maxSum,0));
        sc.close();
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        sc.nextLine();
        int[] preorder = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] inorder = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).
                        toArray();

        TreeNode root = construct(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
        int ntc=Integer.parseInt(sc.nextLine());

        for (int i = 0; i < ntc; i++) {
           int[] num= Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            List<Integer> integers = distanceK(root, new TreeNode(num[0]), num[1]);
            StringJoiner sj = new StringJoiner(" ");
            for(int n:integers)
            {
                sj.add(String.valueOf(n));
            }
            System.out.println(sj);
        }
        sc.close();
    }

    public  static  void preorder(TreeNode node)
    {
        if(node != null)
        {
            System.out.println(node.val);
            preorder(node.left);
            preorder(node.right);
        }
    }
    public  static  Enc  maxBST(TreeNode node)
    {
        if(node== null)
        {
            return  new Enc(Integer.MAX_VALUE,Integer.MIN_VALUE,0,0,true,0,0);
        }
        if(node.left == null && node.right == null)
        {
            return new Enc(node.val, node.val, node.val, node.val, true,1,1);
        }
        Enc lEnc= maxBST(node.left);
        Enc rEnc= maxBST(node.right);
        Enc curr = new Enc();
        curr.isBST = lEnc.isBST && rEnc.isBST && lEnc.upper< node.val && node.val< rEnc.lower;
        if(node.left == null)
        {
            curr.lower = node.val;
        }
        else
        {
            curr.lower = lEnc.lower;
        }
        if(node.right == null)
        {
            curr.upper= node.val;
        }
        else
        {
            curr.upper = rEnc.upper;
        }
        curr.runSum = node.val + lEnc.runSum+ rEnc.runSum;
        curr.nc =  lEnc.nc + rEnc.nc +1;

        if(curr.isBST)
        {
            curr.maxSum = Arrays.stream(new int[] {curr.runSum, lEnc.maxSum, rEnc.maxSum}).max().getAsInt();
            curr.maxnc =  Arrays.stream(new int[] {curr.nc, lEnc.maxnc, rEnc.maxnc}).max().getAsInt();
        }
        else
        {
            curr.maxSum = Math.max(lEnc.maxSum, rEnc.maxSum);
            curr.maxnc =  Math.max(lEnc.maxnc, rEnc.maxnc);
        }
        return curr;

    }
    static class Enc
    {
        int lower;
        int upper;
        int runSum;
        int maxSum;
        boolean isBST;
        int nc;
        int maxnc;
        public  Enc()
        {
        }

        public Enc(int lower, int upper, int runSum, int maxSum, boolean isBST,int nc,int maxnc) {
            this.lower = lower;
            this.upper = upper;
            this.runSum = runSum;
            this.maxSum = maxSum;
            this.isBST = isBST;
            this.nc = nc;
            this.maxnc = maxnc;
        }
    }
    public  static  TreeNode construct(int[] preorder,int[] inorder,int ps,int pe,int is,int ie)
    {
        if(ie<is)
        {
            return  null;
        }
        if(is== ie)
        {
            return  new TreeNode(inorder[is]);
        }
        int idx = find(inorder,preorder[ps],is,ie);
        TreeNode node = new TreeNode(inorder[idx]);
        node.left = construct(preorder,inorder,ps+1,ps+ idx-is,is,idx-1);
        node.right= construct(preorder,inorder,ps+idx-is+1,pe,idx+1,ie);
        return  node;
    }

    public  static  int find(int[] inorder,int key,int is,int ie)
    {
        for (int i = is; i <=ie ; i++) {
            if(inorder[i] == key)
                return  i;
        }
        return  -1;
    }

    static class TreeNode
    {
          int val;
     TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }

    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        map = new HashMap<>();
        fam(root,target);
        Deque<Pair> deque = new ArrayDeque<>();
        deque.offer(new Pair(src,0));
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        while (!deque.isEmpty())
        {
            Pair current = deque.poll();

            if(visited.contains(current.node.val))
            {
                continue;
            }
            visited.add(current.node.val);
            if(current.dist==k)
            {
                result.add(current.node.val);
            }
            //visit left
            if(current.node.left!=null)
            {
                if(!visited.contains(current.node.left.val))
                {
                    deque.offer(new Pair(current.node.left,current.dist+1));
                }
            }
            //visit right

            if(current.node.right!=null)
            {
                if(!visited.contains(current.node.right.val))
                {
                    deque.offer(new Pair(current.node.right,current.dist+1));
                }
            }
            //visit parent
            TreeNode treeNode = map.get(current.node.val);
            if(treeNode!=null && !visited.contains(treeNode.val))
            {
                deque.offer(new Pair(treeNode,current.dist+1));
            }
        }
        return result;
    }
    static HashMap<Integer,TreeNode> map = new HashMap<>();
    static TreeNode src = null;
    public  static  void fam(TreeNode root,TreeNode target) {
        if (root == null)
        {
            return;
        }
        if(root.val == target.val)
        {
            src = root;
        }
        if(root.left != null)
        {
            map.put(root.left.val,root);
        }
        if(root.right != null)
        {
            map.put(root.right.val,root);
        }
        fam(root.left,target);
        fam(root.right,target);
    }
    static  class Pair
    {
        TreeNode node;
        int dist;

        public Pair(TreeNode node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}






















