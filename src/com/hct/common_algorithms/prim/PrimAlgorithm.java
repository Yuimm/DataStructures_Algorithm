package com.hct.common_algorithms.prim;

import java.util.Arrays;

/**
 * @Author HCT
 * @Date 2021/4/9 19:08
 * @Version 1.0
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图的创建
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };

        //创建MGraph对象
        MGraph mGraph = new MGraph(verxs);
        //创建MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);

        minTree.showGraph(mGraph);

        //测试prim
        minTree.prim(mGraph, 0);


    }
}

//创建最小生成树
class MinTree{

    /**
     * 创建图的邻接矩阵
     * @param graph 图对应的结点个数
     * @param verxs 图的各个结点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char[] data,int[][] weight){
        int i, j;
        for (i = 0;i < verxs;i++){
            graph.data[i] = data[i];
            for (j = 0;j < verxs;j ++){
                graph.weight[i][j] = weight[i][j];
            }
        }

    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }


    /**
     * prim算法
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph,int v){
        //标记顶点是否被访问过
        int[] visited = new int[graph.verxs];
         // visited[]默认值为0，表示没有访问过，java中可以不设置，因为默认为0
//        for (int i = 0;i < graph.verxs;i++){
//            visited[i ] = 0;
//        }

        //将当前结点标记为已访问-
        visited[v] = 1;
        //h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minweight = 10000; //先将minweight初始成一个较大的数，后面在遍历的时候，会被替换
        //i表示被访问过的结点，j表示还没被访问过的结点
        for (int k = 1; k < graph.verxs; k++) { //因为有graph.verxs个顶点，经过prim算法，会有graph.verxs - 1条边
            for (int i = 0; i < graph.verxs; i++) {
                //确定每一次生成的子图和哪个结点的距离更近
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minweight) {
                        minweight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小的
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minweight);
            //将当前这个顶点标记为已经访问
            visited[h2] = 1;
            //minWeight 重新置为10000
            minweight = 10000;
        }



    }


}


class MGraph {
    int verxs;  //表示图的结点个数
    char[] data;  //存放结点数据
    int[][] weight;  //存放边，就是邻接矩阵

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
