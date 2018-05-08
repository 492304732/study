package com.susu.study.leetcode;

import java.util.*;

/**
 * @Description: 中级算法：数组
 * @author: 01369674
 * @date: 2018/4/19
 */
public class Solution2_1 {

    /**
     * For example, given array S = {-1 0 1 2 -1 -4},
     * A solution set is:
     * (-1, 0, 1)
     * (-1, -1, 2)
     *
     * 题目大意：
     * 给定一个n个元素的数组，是否存在a，b，c三个元素，使用得a+b+c=0，找出所有符合这个条件的三元组
     *
     * 注意：
     *   - 三元组中的元素必须是非递减的
     *   - 结果不能包含重复元素
     */

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if ((nums[i] + nums[j] + nums[k]) == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    resultSet.add(list);
                    j++;
                    k--;
                } else if ((nums[i] + nums[j] + nums[k]) < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return new ArrayList<List<Integer>>(resultSet);
    }


   /**
    * 相关：两数之和
    * @题目: 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
    * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
    * @示例:
    * 给定 nums = [2, 7, 11, 15], target = 9
    * 因为 nums[0] + nums[1] = 2 + 7 = 9
    * 所以返回 [0, 1]
    * @思路：
    * 两层for循环时间复杂度是O(N)的想法大家应该都会，想想有没有时间复杂度更低的解法呢？
    * 答案就是利用hashmap，这样可以用hashmap的key记录差值，value记录下标，在hashmap中进行查找的时间复杂度是O(1)，这样总的时间复杂度是O(N)。
    */

    public int[] twoSum(int[] a, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            if(map.containsKey(target-a[i])){
                res[0] = map.get(target-a[i]);
                res[1] = i;
                break;
            }
            map.put(a[i],i);
        }
        return res;
    }


    /**
     * @Title: 矩阵置零
     * @Description: 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] index = new int[m+n];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==0){
                    index[i]=1;
                    index[n+j]=1;
                }
            }
        }

        for(int i=0;i<n;i++){
            if(index[i]==1){
                for(int j=0;j<m;j++){
                    matrix[i][j]=0;
                }
            }
        }

        for(int j=0;j<m;j++){
            if(index[n+j]==1){
                for(int i=0;i<n;i++){
                    matrix[i][j]=0;
                }
            }
        }

    }


    /**
     * 字谜分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        for(String s:strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<String>());
            }
            List<String> list = map.get(key);
            list.add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }


    /**
     * @Titile: 无重复字符的最长子串
     * @Description: 给定一个字符串，找出不含有重复字符的最长子串的长度。
     * @Example:
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        char[] chars = s.toCharArray();
        int maxLength=0;
        int currentLength=0;
        int pre;
        for(int i=0;i<chars.length;i++){
            if(map.containsKey(chars[i])){
                pre = i-currentLength;
                if(pre>map.get(chars[i])){
                    currentLength++;
                }else{
                    currentLength=i-map.get(chars[i]);
                }
            }else {
                currentLength++;
            }
            if(currentLength>maxLength){
                maxLength=currentLength;
            }
            map.put(chars[i],i);
        }
        return maxLength;
    }

    /**
     * @Title: 最长回文子串
     * @Description: 给定一个字符串s，找到s中最长的回文子串。你可以假设s的最大长度为1000。
     */
    public String longestPalindrome(String s) {
        int pos;
        int maxRight;
        return null;
    }


}
