package com.susu.study.j2se.api;

import java.math.BigInteger;

public class TestBigInteger {
    public static void main(String[] args){
    	test();
    	test2();
	}

    /**
     * 两个最大Integer相加
     */
    public static void test() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int sum = a + b;

        System.out.println("最大Integer的占用字节");
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println();

        System.out.println("使用Integer相加的结果：");
        System.out.println("a=" + a + ",b=" + b + ",sum=" + sum);
        System.out.println();

        BigInteger a1 = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger a2 = BigInteger.valueOf(Integer.MAX_VALUE);
        System.out.println("使用BigInteger相加的结果：");
        System.out.println("a1=" + a + ",b1=" + b + ",sum=" + a1.add(a2));
        System.out.println();
    }

    public static void test2() {
        System.out.println("MIN_VALUE of int:" + Integer.toBinaryString(Integer.MIN_VALUE) + " " + Integer.MIN_VALUE);
        System.out.println("MAX_VALUE of int:" + Integer.toBinaryString(Integer.MAX_VALUE) + " " + Integer.MAX_VALUE);
        System.out.println();

        int a = 0x11;
        System.out.println("a:" + Integer.toBinaryString(a) + " " + a);

        // 负数的补码等于正数的反码加一，
        // 最小负数不符合这个规则，最小负数的绝对值比最大正数大1，这样做避免了出现两个值为0的编码
        System.out.println("-a:" + Integer.toBinaryString(-a) + " " + -a);
    }
}
