package com.susu.study.jvm.monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Jconsole 监控堆内存情况代码
 * @Args: JVM参数：-Xms100m -Xmx100m -XX:+UseSerialGC
 * @author: 01369674
 * @date: 2018/4/28
 */

/**
 * 内存占位对象，一个 OOMObject 对象大约 64KB
 */
public class OOMObject {
    public byte[] placeholder = new byte[64 * 1024];

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //稍作延时，让监视曲线变化更明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
