package com.susu.study.jvm.monitor;

/**
 * @Description: 引用计数法无法解决循环引用, 可达性分析算法可以解决
 * @Args: -XX:+PrintGCDetails 输出 gc 详细信息
 * @author: 01369674
 * @date: 2018/4/18
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;

    //这个对象的意义是占用内存，以便在GC日志中看清楚是否被回收过
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();
    }
}
