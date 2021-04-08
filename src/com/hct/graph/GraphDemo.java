package com.hct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author HCT
 * @Date 2021/4/8 17:41
 * @Version 1.0
 */
public class GraphDemo {


    public static void main(String[] args) {

        //测试图是否创建完成
        int n = 8;
        //String[] Vertexs = {"A","B","C","D","E"};
        String[] Vertexs = {"1","2","3","4","5","6","7","8"};
        //创建图的对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String vertes : Vertexs){
            graph.insertVertex(vertes);
        }
        //添加边
//        graph.insertEdge(0,1,1);
//        graph.insertEdge(0,2,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(1,3,1);
//        graph.insertEdge(1,4,1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        //显示图的矩阵
        graph.showGraph();

        //测试深度遍历
        graph.dfs();
        System.out.println();

        //测试广度遍历
        graph.bfs();

    }

}


class Graph {

    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应的邻接矩阵
    private int numOfEdges; //表示边的数目
    private boolean[] isVisited; //定义数组，记录某个结点是否被访问

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;

    }
    /**
     * 图的对象构建---插入节点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 图的对象构建---添加边
     *
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 图中常用的方法
     *
     * @return 结点的个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 图中常用的方法
     *
     * @return 边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 图中常用的方法
     *
     * @return 返回结点i（下标）对应的数据 0->"A,1->"B,2->"C"
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 图中常用的方法
     *
     * @return 返回 v1 和 v2 的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 图中常用的方法
     *
     * @return 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 图的深度遍历---得到第一个邻结点的下标
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则就返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 图的深度遍历---根据前一个邻结点的下标来获取下一个邻接结点
     *
     * @param v1
     * @param v2
     * @return 如果存在就返回对应的下标，否则就返回-1
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 图的深度遍历算法
     * @param isVisited
     * @param i
     */
    public void dfs(boolean[] isVisited, int i) {
        //首先访问该节点，并输出
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点 w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {//如果没有被访问过
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 对dfs进行一个重载，遍历多有的结点，并进行dfs
     */
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs--回溯
        for (int i = 0;i < getNumOfVertex();i++){
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /**
     * 对一个结点进行广度优先遍历
     * @param isVisited
     * @param i
     */
    public void bfs(boolean[] isVisited,int i){
        int u; //表示队列的头节点对应的下标
        int w; //邻接结点 w
        //队列：记录结点访问的顺序
        LinkedList<Object> queue = new LinkedList<>();
        //访问结点并输出结点信息
        System.out.print(getValueByIndex(i) + "->");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻结点的下标 w
            w = getFirstNeighbor(u);
            while (w != -1){ //找到结点
                //是否被访问过
                if(!isVisited[w]){ //未被访问过
                    System.out.print(getValueByIndex(w) + "->");
                    //标记为已访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //已经被访问过，以 u 为前驱结点，找 w 的下一个邻结点
                w = getNextNeighbor(u,w);
            }
        }
    }

    /**
     * 遍历所有的结点，都进行广度优先遍历
     */
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0;i<getNumOfVertex();i++){
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

}