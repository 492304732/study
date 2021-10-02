package com.susu.study.jvm.monitor;

import java.io.IOException;

/**
 * @Description: 测试线程等待
 * @Args: -Xms20m -Xmx20m
 * @author: 01369674
 * @date: 2018/5/2
 */
public class BusyThread {

    /**
     * 线程死循环演示
     */
    public static Thread createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                int i = 0;
                while (true) {
                    if (Thread.interrupted()) {
                        break;
                    }
                    i++;
                    if (i % 100000000 == 0) {
                        long curTime = System.currentTimeMillis();
                        System.out.println("busyThread i=" + i + " time=" + (curTime - startTime));
                    }
                }
            }
        }, "testBusyThread");
        thread.start();
        return thread;
    }

    /**
     * 线程锁等待演示
     *
     * @param lock
     */
    public static Thread createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
        return thread;
    }

    public static void main(String[] args) throws IOException {
        Thread busyThread = createBusyThread();
        Object obj = new Object();
        Thread lockThread = createLockThread(obj);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 结束 busyThread
        busyThread.interrupt();

        // 结束 lockThread
        synchronized (obj) {
            obj.notifyAll();
            System.out.println("notifyAll");
        }
    }

}
