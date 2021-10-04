package com.susu.study.j2se.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 清楚 try-catch-finally 的执行顺序，则很容易得出结论。实际上三个子句中都可以写 return，只是不推荐。
 */
public class TestTryCatch {
    public static void main(String[] args) {
        /**
         * 输出：reach1，reach3，返回值是3
         * 原因：在执行 try 中 return 语句之前先执行 finally 语句
         */
        System.out.println(test1("2015-10-3"));
        System.out.println(test1("1"));
        //error:Unparseable date: "1",reach2,reach3,3
        System.out.println();

        System.out.println(test2("2015-10-3")); //reach1,reach3,1
        System.out.println(test2("1"));
        // error:Unparseable date: "1",reach2,reach3,2
        System.out.println();

        System.out.println(test3("2015-10-3")); //reach1,reach3,4
        System.out.println(test3("1"));
        //error:Unparseable date: "1",reach2,reach3,2
        System.out.println();

        System.out.println(test4());
    }

    /**
     * finally 中写 return 语句会报警告，不推荐在 finally 中 return
     * 如果在 finally 中写 return，则 try-catch 后面的语句不会执行
     * 如果在 finally 中写 return，则  try 子句和 catch 子句中的 return 不会执行，如本例，不会出现 return 1 和 return 2 的情况
     */
    private static int test1(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd");

        try {
            dateFormat.parse(date);
            System.out.print("reach" + 1 + ",");
            return 1;
        } catch (ParseException e) {
            System.out.print("error:" + e.getMessage() + ",");
            System.out.print("reach" + 2 + ",");
            return 2;
        } finally {
            System.out.print("reach" + 3 + ",");
            return 3;
        }
        // 报错：Unreachable code
        //		return 4;
    }

    private static int test2(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd");

        try {
            dateFormat.parse(date);
            System.out.print("reach" + 1 + ",");
            return 1;
        } catch (ParseException e) {
            System.out.print("error:" + e.getMessage() + ",");
            System.out.print("reach" + 2 + ",");
            return 2;
        } finally {
            System.out.print("reach" + 3 + ",");
        }

        // 报错：Unreachable code
        //		return 4;
    }

    private static int test3(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd");

        try {
            dateFormat.parse(date);
            System.out.print("reach" + 1 + ",");
        } catch (ParseException e) {
            System.out.print("error:" + e.getMessage() + ",");
            System.out.print("reach" + 2 + ",");
            return 2;
        } finally {
            System.out.print("reach" + 3 + ",");
        }
        return 4;
    }

    /**
     * finally 子句不是在 return 前执行，而是在 return 中间执行
     * try 执行到 return 这行时，1. 先把 x 储存起来准备返回    2.执行finally  3.将之前储存的返回值返回
     *
     * @return
     */
    public static int test4() {
        int x = 0;
        try {
            return ++x;
        } finally {
            ++x;
            System.out.print("finally:" + x + "  return:");
        }
    }

}
