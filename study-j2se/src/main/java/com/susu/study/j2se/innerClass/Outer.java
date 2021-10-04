package com.susu.study.j2se.innerClass;

/**
 * 几种内部类的用法
 */
public class Outer {
    private int outer_x = 1;
    public int outer_y = 2;

    // 非静态内部类
    private class Inner_2 {
        private int private_inner_x;

        public void publicMethod() {
            System.out.print("publicMethod:");
            System.out.print(" outer_x: " + outer_x);
            System.out.print(" outer_y: " + outer_y);
            System.out.println();
        }
    }

    // 静态内部类
    private static class Inner_4 {
        private int private_inner_x;

        public void publicMethod() {
            System.out.print("publicMethod:");
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Outer outer = new Outer();
        outer.test1();
        outer.test2();
        outer.test3();
        Thread.sleep(500);
        outer.test4();
    }

    /**
     * 内部类在方法内定义:类似局部变量
     * 不能用 public、private 等修饰，只能用 final 和 abstract 修饰。
     * 这种内部类对其他类是不可见的其他类无法引用这种内部类，但是这种内部类创建的实例对象可以传递给其他类访问。
     * 这种内部类必须是先定义，后使用。
     * 这种内部类可以访问方法体中的局部变量，但是，该局部变量前必须加final修饰符。
     * <p>
     * 成员变量和方法的 public、private 区别：能否被除了本类的其他类访问
     */
    public void test1() {
        // 被内部类引用的局部变量一定要是 final 的
        final int temp = 123;

        // 不能用 public、private 等修饰，只能用 final 和 abstract 修饰。
        // 报错：Illegal modifier for the local class Inner; only abstract or final is permitted
        // public class Inner{}
        class Inner_1 {
            private int private_inner_x = 1;
            public int public_inner_y = 2;

            public void publicMethod() {
                System.out.print("publicMethod:");
                System.out.print(" outer_x: " + outer_x);
                System.out.print(" outer_y: " + outer_y);
                System.out.print(" final temp: " + temp);
                System.out.println();
            }
        }

        System.out.println("方法体内定义内部类：");
        // 直接创建
        Inner_1 inner_1 = new Inner_1();
        // 内部类访问外部类的成员变量（公有和私有）
        inner_1.publicMethod();

        // 方法中访问内部类的成员变量（共有和私有）
        System.out.print("inner menber:");
        System.out.print(" private_inner_x:" + inner_1.private_inner_x);
        System.out.print(" public_inner_y:" + inner_1.public_inner_y);

        System.out.println();
    }

    /*
     * 内部类在方法外定义：类似于成员变量
     * 可以加访问控制权限，决定这个内部类的定义对其他类是否可见
     */
    public void test2() {
        // 必须在有外部类实例的情况下建立内部类
        Outer outer = new Outer();
        Outer.Inner_2 inner2 = outer.new Inner_2();

        System.out.println();
        System.out.println("方法体外定义内部类：");
        inner2.publicMethod();
        System.out.println();
    }

    /*
     * 匿名内部类
     * 即定义某一接口或类的子类的同时，还创建了该子类的实例对象，无需为该子类定义名称
     *
     */
    public void test3() {
        System.out.println("匿名内部类：");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");

            }
        });

        thread.start();
    }

    /*
     * 静态内部类: 类似普通类
     * 它可以定义成public、protected、默认的、private等多种类型，而普通类只能定义成public和默认的这两种类型。
     */
    public void test4() {
        System.out.println();
        System.out.println("静态内部类：");
        Outer.Inner_4 inner_4 = new Outer.Inner_4();
        inner_4.publicMethod();
    }
}
