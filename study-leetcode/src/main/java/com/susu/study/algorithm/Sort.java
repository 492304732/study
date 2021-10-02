package com.susu.study.algorithm;

import java.util.Arrays;

/**
 * @Description: 排序算法
 * @author: 01369674
 * @date: 2018/4/23
 */
public class Sort {

    /**
     * 快速排序
     *
     * @思路:
     * 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
     * 2．j–由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
     * 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
     * 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
     *
     */
    public void quickSort(int[] nums){
        quickSort(nums,0,nums.length-1);
    }

    private void quickSort(int[] nums,int i,int j){
        if(i<j){
            int seg = partition(nums,i,j);
            quickSort(nums,i,seg-1);
            quickSort(nums,seg+1,j);
        }
    }

    private int partition(int[] nums,int i,int j){
        int segNum = nums[i];

        while (i<j){
            while(i<j && nums[j]>=segNum){
                j--;
            }
            nums[i]=nums[j];
            while(i<j && nums[i]<=segNum){
                i++;
            }
            nums[j]=nums[i];
        }

        nums[i]=segNum;
        return i;
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] nums={2,0,1};
        sort.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
