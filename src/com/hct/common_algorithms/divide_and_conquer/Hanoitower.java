package com.hct.common_algorithms.divide_and_conquer;

/**
 * 分治算法---汉诺塔
 * @Author HCT
 * @Date 2021/4/8 22:57
 * @Version 1.0
 */
public class Hanoitower {
    public static void main(String[] args) {
        //测试汉诺塔
        hanoiTower(3,'A', 'B', 'C');


    }

    public static void hanoiTower(int num,char a ,char b,char c){
         if (num == 1){
             System.out.println("第1个盘从 " + a + "->" + c);
         }else {
             //如果有 n >= 2,总是看作两个盘 1.最下面一个盘  2.上面的所有盘
             //1.先把最上面的所有盘从 A->B ,移动过程会用到C
             hanoiTower(num-1,a,c,b);
             //2.把最下面的盘从 A->C
             System.out.println("第" + num + "个盘从 " + a + "->" + c);
             //3.把B塔的所有盘从 B->C,移动的过程需要用到A塔
             hanoiTower(num-1,b,a,c);

         }
    }
}
