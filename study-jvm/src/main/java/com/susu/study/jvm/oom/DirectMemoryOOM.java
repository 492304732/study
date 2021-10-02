package com.susu.study.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description: 直接内存区溢出，抛出 OOM,但是在 Heap Dump 文件中不会看见明显异常。注意使用 NIO的情况。
 * @Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * -Xmx:最大堆大小
 * @author: 01369674
 * @date: 2018/4/18
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        int count = 0;
        while (count < Integer.MAX_VALUE) {
            unsafe.allocateMemory(_1MB);
        }
    }
}


/*
 * 运行结果：
 * Exception in thread "main" java.lang.OutOfMemoryError: Unable to allocate 1048576 bytes
 * 	at java.base/jdk.internal.misc.Unsafe.allocateMemory(Unsafe.java:632)
 * 	at jdk.unsupported/sun.misc.Unsafe.allocateMemory(Unsafe.java:462)
 * 	at com.susu.study.jvm.oom.DirectMemoryOOM.main(DirectMemoryOOM.java:22)
 */