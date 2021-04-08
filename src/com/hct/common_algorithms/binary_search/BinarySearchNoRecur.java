package com.hct.common_algorithms.binary_search;

/**
 * 二分查找的非递归实现
 *
 * @Author HCT
 * @Date 2021/4/8 21:51
 * @Version 1.0
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {

        //测试
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr,11);
        System.out.println(index);

    }

    /**
     *
     * @param arr  待查找的数组,升序数组
     * @param target 要查找的值
     * @return 返回对应的下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
