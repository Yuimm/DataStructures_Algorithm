package com.hct.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author HCT
 * @Date 2021/3/23 11:19
 * @Version 1.0
 */
public class HeapSort {
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

        hearSort(arr);


        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println("排序后的时间==" + format2);

    }

    //一个堆排序的方法
    public static void hearSort(int[] arr){
        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0; i--){
            adjustHeap(arr,i,arr.length);
        }


        /*
         * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
         */
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        //System.out.println("数组=" + Arrays.toString(arr));

    }


    /**
     * 数组构建大顶堆
     * @param arr 待调整的数组
     * @param i 表示非叶子结点再数组中的索引
     * @param length 表示对多少个元素继续调整
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];// 先取出当前元素的值，保存在临时变量
        // 开始调整
        // 说明
        // 1. k = i * 2 + 1 ：k 是 i结点的左子结点
        for (int k = i * 2 + 1;k < length;k = k * 2 + 1){
            if (k + 1 < length && arr[k] < arr[k+1]){  // 说明左子结点的值小于右子结点的值
                k++; // k 指向右子结点
            }
            if (arr[k] > temp){  // 如果子结点大于父结点
                arr[i] = arr[k]; // 把较大的值赋给当前结点
                i = k;    // !!! i 指向 k，将小的值沉下去
            } else {
                break; // !!! 由于是从最深处往前调整，我能保证下面的子树已经是大顶堆了
            }
        }
        // 当for 循环结束后，我们已经将以i 为父结点的树的最大值，放在了 最顶(局部)
        arr[i] = temp;// 将temp值放到调整后的位置
    }
}
