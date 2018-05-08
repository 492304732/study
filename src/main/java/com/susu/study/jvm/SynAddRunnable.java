package com.susu.study.jvm;

/**
 * @Description: 线程死锁等待演示
 * @author: 01369674
 * @date: 2018/5/2
 */
public class SynAddRunnable implements Runnable {
    private int a,b;
    public SynAddRunnable(int a,int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)){
            synchronized (Integer.valueOf(b)){
                System.out.println(a+b);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunnable(1,2)).start();
            new Thread(new SynAddRunnable(2,1)).start();
        }
    }
}
