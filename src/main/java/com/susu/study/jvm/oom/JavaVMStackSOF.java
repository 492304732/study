package com.susu.study.jvm.oom;

/**
 * @Args: VM Args: -Xss128k
 * -Xss 设置虚拟机栈大小
 * @Description: 线程请求的栈深度大于虚拟机栈允许的最大深度，抛出 StackOverflowError 异常
 * @author: 01369674
 * @date: 2018/4/18
 */
public class JavaVMStackSOF {

    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try{
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:"+oom.stackLength);
            throw e;
        }

    }
}

/*
 * stack length:2284
 * Exception in thread "main" java.lang.StackOverflowError
 */
