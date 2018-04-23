package com.susu.study.leetcode;

import java.util.*;

/**
 * @Description: 初级算法：数组
 * @author: 01369674
 * @date: 2018/4/19
 */
public class Solution1_1 {

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length==0||nums.length==1){
            return nums.length;
        }
        int count = 1;
        int size = nums.length;
        int i=1;
        while(i<size){
            if(nums[i]==nums[i-1]){
                remove(nums,i);
                size --;
            }else{
                count++;
                i++;
            }
        }
        return count;
    }

    private void remove(int[] nums,int index){
        for(int i=index;i<nums.length-1;i++){
            nums[i]=nums[i+1];
        }
    }


    /**
     * 假设有一个数组，它的第 i 个元素是一个给定的股票在第 i 天的价格。
     * 设计一个算法来找到最大的利润。你可以完成尽可能多的交易（多次买卖股票）。然而，你不能同时参与多个交易（你必须在再次购买前出售股票）。
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        for(int i=1; i<prices.length; i++){
            if(prices[i] > prices[i-1]){
                sum = sum + prices[i] - prices[i-1];
            }
        }
        return sum;
    }


    /**
     * 将包含 n 个元素的数组向右旋转 k 步。
     * 例如，如果  n = 7 ,  k = 3，给定数组  [1,2,3,4,5,6,7]  ，向右旋转后的结果为 [5,6,7,1,2,3,4]。
     *
     * @思路：
     * 假设原数组序列为abcd1234，要求变换成的数组序列为1234abcd，即循环右移了4位。比较之后，不难看出，其中有两段的顺序是不变的：1234和abcd，可把这两段看成两个整体。右移K位的过程就是把数组的两部分交换一下。变换的过程通过以下步骤完成：
     * 逆序排列abcd：abcd1234 → dcba1234；
     * 逆序排列1234：dcba1234 → dcba4321；
     * 全部逆序：dcba4321 → 1234abcd。
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        //旋转后下标值为0：(index+k)%n=0,则index=n-k
        int index = (n-k)%n;
        if(index<0){
            index=index+n;
        }
        convert(nums,index,nums.length-1);
        convert(nums,0,index-1);
        convert(nums,0,nums.length-1);
    }

    private void convert(int[] nums,int start,int end){
        int m=start;
        int n=end;
        int temp;
        while(m<n){
            temp = nums[m];
            nums[m]=nums[n];
            nums[n]=temp;
            m++;
            n--;
        }
    }

    /**
     * @Title： 存在重复
     * @Description: 给定一个整数数组，判断是否存在重复元素。
     * 如果任何值在数组中出现至少两次，函数应该返回 true。如果每个元素都不相同，则返回 false。
     */
    public boolean containsDuplicate(int[] nums) {
        Boolean result = false;
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i <nums.length ; i++) {
            if(set.contains(nums[i])){
                result=true;
                break;
            }else {
                set.add(nums[i]);
            }
        }
        return result;
    }


    /**
     * @title: 只出现一次的数字
     * @Description:
     * 给定一个整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * @Notice：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     */
    public int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result^=nums[i];
        }
        return result;
    }


    /**
     * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
     * 最高位数字存放在数组的首位，数组中每个元素只存储一个数字。
     * 你可以假设除了整数 0之外，这个整数不会以零开头。
     */
    public int[] plusOne(int[] digits) {
        int i=0;
        i= digits.length-1;
        int[] result = digits.clone();
        while(i>=0){
            if(result[i]!=9){
                result[i]+=1;
                return result;
            }else{
                result[i]=0;
                i--;
            }
        }

        result = new int[digits.length+1];
        result[0]=1;

        return result;
    }


    /**
     * 有效的数独
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] validMapHorizontal  = new boolean[9][9];
        boolean[][] validMapVertical = new boolean[9][9];
        boolean[][] validMapCube = new boolean[9][9];
        int[][] cubeIndex = {{0,1,2},{3,4,5},{6,7,8}};
        int value;
        int index;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    continue;
                }
                value = board[i][j]-48;
                index = cubeIndex[i/3][j/3];
                if(validMapHorizontal[i][value-1] || validMapVertical[j][value-1] || validMapCube[index][value-1]){
                    return false;
                }else {
                    validMapHorizontal[i][value-1]=true;
                    validMapVertical[j][value-1]=true;
                    validMapCube[index][value-1]=true;
                }
            }
        }
        return true;
    }

    /**
     * 旋转图像
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * 说明：你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * 思路：先对角翻转，然后上下翻转
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int temp;

        //对角翻转，遍历矩阵的左上角
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                temp = matrix[i][j];
                matrix[i][j]=matrix[n-1-j][n-1-i];
                matrix[n-1-j][n-1-i] = temp;
            }
        }

        //上下翻转
        for(int j=0;j<n;j++){
            for(int i=0;i<n/2;i++){
                temp = matrix[i][j];
                matrix[i][j]=matrix[n-i-1][j];
                matrix[n-i-1][j] = temp;
            }
        }

    }


    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     * 例如，
     * s = "anagram"，t = "nagaram"，返回 true
     * s = "rat"，t = "car"，返回 false
     */
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        String sorts = String.valueOf(chars1);
        String sortt = String.valueOf(chars2);
        if(sorts.equals(sortt)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 字符串转整数
     */
    public int myAtoi(String str) {
        Set<Character> digitSet = new HashSet<Character>();
        Character[] digits = {'0','1','2','3','4','5','6','7','8','9'};
        Collections.addAll(digitSet,digits);

        char[] chars = str.toCharArray();
        if(chars.length==0){
            return 0;
        }
        int i=0;
        while(i<chars.length){
            if(chars[i]==' '){
                i++;
            }else{
                break;
            }
        }

        //第一个非空字符是正负符号时，验证符号后的字符是不是数字
        if(chars[i]=='-'||chars[i]=='+'){
            if((i+1)>=chars.length || !digitSet.contains(chars[i+1])){
                return 0;
            }
        }else if(!digitSet.contains(chars[i])){
            //第一个非空字符不是正负符号，也不是数字
            return 0;
        }

        int start = i;
        int end = chars.length-1;
        i++;
        for(;i<chars.length;i++){
            if(!digitSet.contains(chars[i])){
                end = i-1;
                break;
            }
        }

        int result = 0;
        try {
             result = Integer.valueOf(String.valueOf(chars,start,end-start+1));
        } catch (NumberFormatException e) {
            if(chars[start]=='-'){
                result = Integer.MIN_VALUE;
            }else {
                result = Integer.MAX_VALUE;
            }
        }

        return result;
    }

    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，
     * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回  -1。
     */
    public int strStr(String haystack, String needle) {
        haystack.contains(needle);
        return 0;
    }


    public static void main(String[] args) {
        String s = "4193 with words";
        char[] chars = s.toCharArray();
        if(chars[4]==' '){
            System.out.println("123");
        }else if(chars[4]==32){
            System.out.println("32");
        }
        Solution1_1 solution = new Solution1_1();
        solution.myAtoi(s);
    }
}
