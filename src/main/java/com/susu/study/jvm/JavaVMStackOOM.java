package com.susu.study.jvm;

/**
 * @Description: 虚拟机在扩展栈时无法申请到足够的内存空间，则抛出 OutOfMemoryError 异常
 * @Args: VM Args: -Xss2M
 * @author: 01369674
 * @date: 2018/4/18
 */
public class JavaVMStackOOM {
    private void dontStop(){
        int count=0;
        while(count<Integer.MAX_VALUE){
            count++;
        }
    }

    public void stackLeakByThread(){
        int count=0;
        while (count<Integer.MAX_VALUE){
            count++;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}

/*
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 */
