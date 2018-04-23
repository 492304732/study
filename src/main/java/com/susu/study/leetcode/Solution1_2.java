package com.susu.study.leetcode;

/**
 * @Description: 初级算法：字符串
 * @author: 01369674
 * @date: 2018/4/20
 */
public class Solution1_2 {
    /**
     * 反转字符串
     * 请编写一个函数，其功能是将输入的字符串反转过来。
     */
    public String reverseString(String s) {
        char[] chars =s.toCharArray();
        reverseChars(chars,0,chars.length-1);
        return String.valueOf(chars);
    }

    private void reverseChars(char[] chars,int start,int end){
        int n = start;
        int m= end;
        char temp;
        while(n<m){
            temp=chars[n];
            chars[n]=chars[m];
            chars[m]=temp;
            n++;
            m--;
        }
    }


    /**
     * 颠倒整数
     * 给定一个 32 位有符号整数，将整数中的数字进行反转。
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
     */
    public int reverse(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        if(chars[0]=='-'){
            reverseChars(chars,1,chars.length-1);
        }else{
            reverseChars(chars,0,chars.length-1);
        }

        int result =0;
        try{
            result = Integer.valueOf(String.valueOf(chars));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 验证回文字符串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     */
    public boolean isPalindrome(String s) {
        return false;
    }
}
