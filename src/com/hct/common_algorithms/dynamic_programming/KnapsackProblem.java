package com.hct.common_algorithms.dynamic_programming;

import java.awt.*;

/**
 * 动态规划---背包问题
 *
 * @Author HCT
 * @Date 2021/4/9 14:28
 * @Version 1.0
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3}; //物品的重量
        int[] val = {1500,3000,2000}; //物品的价值
        int m = 4; //背包的容量
        int n = val.length; //物品的个数

        //创建二维数组
        //v[i][j] 表示在前i个物品中能够装入重量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
        //为了记录商品的放入情况
        int[][] path = new int[n+1][m+1];


        //初始化第一行和第一列，本程序默认为0
        for (int i = 0;i < v.length;i++){
            v[i][0] = 0; //将第一列初始化为0
        }
        for (int i = 0;i < v.length;i++){
            v[0][i] = 0; //将第一行初始化为0
        }



        //根据前面的公式动态规划处理
        for (int i = 1;i < v.length;i++){ //不处理第一行，i是从1开始的
            for (int j = 1;j < v[0].length;j++){ //不处理第一列，j是从1开始的
                //公式
                if (w[i-1] > j){ //因为程序i是从1开始的，原来公式的 w[i] 修改成 w[i-1]
                    v[i][j] = v[i-1][j];
                }else{
//                    //说明
//                    //因为程序i是从1开始的，所以公式需要调整为下面的样子
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);

                    //为了记录商品存放到背包的情况，不能使用上面的公式，使用 if-else
                    if (v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        //把当前的情况记录到 path
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }

            }
        }

        //输出一个 v 数组
        for (int i = 0;i < v.length;i++){
            for (int j = 0;j < v[i].length;j++){
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

//        //输出最后放入了哪些商品
//        //遍历path,这样输出会把所有的放入情况都得到，其实只需要最后的放入情况
//        for (int i = 0;i < path.length;i++) {
//            fojr (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1){
//                    System.out.printf("第%d个商品放入到背包",i);
//                }
//            }
//        }

        int i  = path.length - 1; //行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while (i > 0 && j > 0){  //从path的最后开始查找
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= w[i-1];
            }
            i--;
        }

    }
}
