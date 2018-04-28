package com.susu.study.jvm.com.susu.study.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Args: VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms 堆的最小值 -Xmx 堆的最大值 -XX:+HeapDumpOnOutOfMemoryError 让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后分析。
 * @Description Java堆溢出 java.lang.OutOfMemoryError: Java heap space
 * @author: 01369674
 * @date: 2018/4/18
 */
public class HeapOOM {
    static class OOMObject{
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        int count = 0;
        //循环创建对象直至内存溢出
        try {
            while(count<Integer.MAX_VALUE){
                list.add(new OOMObject());
                count++;
            }
        }catch (Throwable e) {
            System.out.println("count:"+count);
        }



    }
}

/*
* java.lang.OutOfMemoryError: Java heap space
* Dumping heap to java_pid643456.hprof ...
* Heap dump file created [8831270 bytes in 0.068 secs]
 */