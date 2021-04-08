package com.hct.sort;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 * @Author HCT
 * @Date 2021/3/21 1:56
 * @Version 1.0
 */
public class BubbleSort {
    public static void main(String[] args) {

        // 测试一下冒泡排序的速度O(n^2), 给80000个数据，测试
        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat1.format(date1);
        System.out.println("排序前的时间==" + format1);

        bubbleSort(arr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println("排序后的时间==" + format2);


    }

    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false; //表示变量，表示是否进行过交换
        for (int i = 0;i < arr.length-1;i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag){
                //在一趟排序中，一次交换都没有进行过，即已经完成排序，没必要再继续进行下一趟排序
                break;
            } else {
                flag = false; //重置flag，进行下次判断
            }
        }
    }
}
