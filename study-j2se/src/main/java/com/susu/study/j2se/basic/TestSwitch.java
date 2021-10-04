package com.susu.study.j2se.basic;

/**
 * java 1.6(包括)以前，只是支持等价成int 基本类型的数据:byte ,short,char,int(其他的都不可以）。
 * 特别的，long不可以，因为long不能转换为int
 * 1.7加入的新特性可以支持String类型的数据。
 *
 * 自动装箱和拆箱从Java 1.5开始引入
 * 因此 byte ,short,char,int 的引用类型也可以传
 *
 * 输入Object报错：Cannot switch on a value of type Object.
 * Only convertible int values, strings or enum variables are permitted
 */
public class TestSwitch {
    public static void main(String[] args) {
        testInt(4);

        testString("banana");
        testString("none");
    }

    /**
     * 如果没有 break，则会继续往下执行 case
     * 输入i=4，则会输出 default，2，3。即跳到指定位置后依次向下执行
     */
    public static void testInt(int i) {
        System.out.println("testInt: " + i);
        switch (i) {
            case 1:
                System.out.println("1");
                break;
            default:
                System.out.println("default");
            case 2:
                System.out.println("2");
            case 3:
                System.out.println("3");
        }
        System.out.println();
    }

    public static void testString(String s) {
        System.out.println("testString: " + s);
        switch (s) {
            case "apple":
                System.out.println("apple");
                break;
            default:
                System.out.println("default");
            case "eggs":
                System.out.println("eggs");
            case "banana":
                System.out.println("banana");
        }
        System.out.println();
    }
}
