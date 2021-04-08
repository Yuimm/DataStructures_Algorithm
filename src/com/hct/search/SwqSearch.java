package com.hct.search;

import java.sql.SQLClientInfoException;

/**
 * @Author HCT
 * @Date 2021/3/21 18:03
 * @Version 1.0
 */
public class SwqSearch {
    public static void main(String[] args) {
        int[] arr = { 1,9,11,-1,34,89 };
        int index = seqSearch(arr,12);
        if (index == -1){
            System.out.println("没有找到！");
        } else {
            System.out.println(index);
        }
    }

    /**
     * 这里实现的线性查找是找到一个满足条件的值，就返回
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
