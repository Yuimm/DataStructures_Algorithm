package com.hct.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 * @Author HCT
 * @Date 2021/3/21 11:02
 * @Version 1.0
 */
public class InsertSort {
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

        insertSort(arr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println("排序后的时间==" + format2);
    }


    //插入排序
    public static void insertSort(int[] arr) {

        for (int i = 1;i < arr.length;i++){
            //定义待插入的数,和要插入的位置索引
            int insertVal = arr[i];
            int insertIndex = i - 1;

            //1.insertIndex >= 0 保证在给insertVal找插入位置时，不越界
            //2.insertVal < arr[insertIndex 表示待插入的数，还没有找到要插入的位置
            //3.就需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;

            }
            // 当退出while循环时，说明插入的位置找到, insertIndex + 1
            //判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
