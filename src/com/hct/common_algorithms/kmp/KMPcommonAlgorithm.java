package com.hct.common_algorithms.kmp;

import java.util.Arrays;

/**
 * KMP算法
 *
 * @Author HCT
 * @Date 2021/4/9 15:58
 * @Version 1.0
 */
public class KMPcommonAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));


        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }

    //获取一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建next数组
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串长度为1，部分匹配值为0
        for (int i = 1,j = 0;i < dest.length();i++){
            //当 dest.charAt(i) != dest.charAt(j) 我们需要从 next[j-1]获取新的j
            //指导我们发现有dest.charAt(i) == dest.charAt(j)成立才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            //当 dest.charAt(i) == dest.charAt(j) 这个条件满足时，部分匹配值就是 +1
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }


    /**
     * KMP搜索算法
     * @param str1 源字符串
     * @param str2 匹配的子串
     * @param next 部分匹配表，next数组
     * @return  -1没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1,String str2,int[] next){

        for (int i = 0,j = 0;i < str1.length();i++){
            //处理 str1.charAt(i) == str2.charAt(j)，去调整j的大小
            //核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){ //找到了
                return i - j + 1;
            }
        }
        return -1;
    }

}
