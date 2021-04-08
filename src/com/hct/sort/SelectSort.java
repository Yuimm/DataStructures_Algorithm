package com.hct.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 * @Author HCT
 * @Date 2021/3/21 2:33
 * @Version 1.0
 */
public class SelectSort {
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

        selectSort(arr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println("排序后的时间==" + format2);

    }

    //选择排序
    public static void selectSort(int[] arr) {
        // 使用逐步推导的方式来，讲解选择排序
        // 第1轮
        // 原始的数组 ： 101, 34, 119, 1
        // 第一轮排序 : 1, 34, 119, 101
        // 算法 先简单--》 做复杂， 就是可以把一个复杂的算法，拆分成简单的问题-》逐步解决

        for (int i = 0;i < arr.length - 1; i++) {

            int minIndex = i; //假定最小值的索引为0
            int min = arr[i]; //假定最小值的数为arr[0]
            // j的初始值为 0+1 的原因是，让假定的最小值索引为0位置上的数与他后面的值进行比较
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j]; //重置最小值
                    minIndex = j; //重置最小值索引
                }
            }
            //将最小值放在arr[0],即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

    }
}
