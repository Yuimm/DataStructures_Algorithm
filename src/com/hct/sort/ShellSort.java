package com.hct.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序---交换法
 *
 * @Author HCT
 * @Date 2021/3/21 11:34
 * @Version 1.0
 */
public class ShellSort {
    public static void main(String[] args) {

        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat1.format(date1);
        System.out.println("排序前的时间==" + format1);

        shellSort2(arr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println("排序后的时间==" + format2);

    }



    public static void shellSort(int[] arr){

        int temp = 0;


        for (int gap = arr.length / 2;gap > 0; gap /= 2){
            for (int i = gap; i < arr.length; i++){
                //遍历各组中所有元素（共5组，每组有两个元素），步长为5
                for (int j = i - gap; j >= 0 ; j -= gap){
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }

    }


    public static void shellSort2(int[] arr){
        for (int gap = arr.length / 2;gap > 0; gap /= 2){
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // temp 比 arr[j - gap] 大，所以需要插入在 j 的位置
                    arr[j] = temp;
                }

            }
        }
    }

}
