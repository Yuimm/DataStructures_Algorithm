package com.hct.search;

/**
 * @Author HCT
 * @Date 2021/3/21 19:28
 * @Version 1.0
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int [] arr = new int[100];
        for(int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println("index = " + index);

    }


    /**
     * 插值查找算法，也要求数组是有序的
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找值
     * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        //注意：findVal < arr[left]  和  findVal > arr[right] 必须需要，否则我们得到的 mid 可能越界
        // findVal < arr[left] ：说明待查找的值比数组中最小的元素都小
        // findVal > arr[right] ：说明待查找的值比数组中最大的元素都大
        if (left > right || findVal < arr[left] || findVal > arr[right]) {
            return -1;
        }
        // 求出mid, 自适应，就是一次函数
        // findVal = arr[left] 时，mid = left
        // findVal = arr[right] 时，mid = right
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) { // 说明应该向右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 说明向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }


}
