package com.bm;

public class BitManipulation {

    public static void main(String[] args) {
//        resetBit(5,2);
//        resetBit(5,1);
        pascalSum(1);
        pascalSum(2);
        pascalSum(3);
    }

    public static void extractBit(int n, int i) {
        int k = 1 << i;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n & k));
    }

    public static void setBit(int n, int i) {
        int k = 1 << i;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n | k));
    }

    public static void resetBit(int n, int i) {
        int k = ~(1 << i);
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n & k));

    }

    public static void incrementBy1(int n) {
        int mask = 1;

        while ((n & mask) > 0) {
            n = n ^ mask;
            mask = mask << 1;
        }
        n = n | mask;
        System.out.println(n);
    }

    /**
     * https://www.geeksforgeeks.org/find-nth-magic-number/#:~:text=A%20magic%20number%20is%20defined,125%20%2B%205)%2C%20%E2%80%A6.
     */
    public static void magicNumber(int n)
    {
        int result =0;
        int pow=1;
        while(n>0)
        {
            pow*=5;
            if((n&1) == 1)
            {
                result = (int) (result+ pow);
            }
            n= n>>1;
        }
        System.out.println(result);
    }
    /**
     * https://www.geeksforgeeks.org/sum-of-all-elements-up-to-nth-row-in-a-pascals-triangle/
     */
    public  static  void pascalSum(int n)
    {
        System.out.println(Math.pow(2,n)-1);
    }

    public  static  void powerOfTwo(int n)
    {
        if(n==0)
        {
            System.out.println("Not a power of 2");
            return;
        }
        System.out.println(n&(n-1));
        return;
    }
}
