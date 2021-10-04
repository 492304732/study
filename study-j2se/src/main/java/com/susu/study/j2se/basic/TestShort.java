package com.susu.study.j2se.basic;

public class TestShort {
    /**
     * 6. short s1 = 1; s1 = s1 + 1; 有什么错? short s1 = 1; s1 += 1;有什么错?
     *
     * 对于short s1 = 1; s1 = s1 + 1; 由于s1+1运算时会自动提升表达式的类型，所以结果是int型，
     * 再赋值给short类型s1时，编译器将报告需要强制转换类型的错误。
     *
     * 对于short s1 = 1; s1 += 1; 由于 +=是java语言规定的运算符，java编译器会对它进行特殊处理，因此可以正确编译。
     */
    public void test1() {
        // 报错，需要强制类型转换
        // short j=1;j=j+1;
        short j = 1;
        j = (short) (j + 1);

        // 正常
        short i = 1;
        i += 1;
    }
}
