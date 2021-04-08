package com.hct.SparseArray;

public class SparseArray {
    public static void main(String[] args) {
        /*
        * 二维数组转稀疏数组的思路：
        *  1. 遍历原始的二维数组，得到有效数据的个数sum
        *  2. 根据sum创建稀疏数组 **sparseArr int [sum+1] [3]**
        *  3. 将总的行数、列数、sum存入稀疏数组第一行
        *  4. 再将二维数组的有效数据存入稀疏数组
        * */

        //创建一个原始二维数组 11*11
        //0：没有棋子，1：黑子，2：蓝子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][6] = 12;
        //输出原始二维数组
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //1. 先遍历二维数组得到有效数据个数
        int sum = 0;
        for (int i = 0;i < 11;i++){
            for (int j = 0;j < 11;j++){
                if (chessArr[i][j] != 0){
                    sum++;
                }
            }
        }
        //2.创建稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组，将非0的值存放到spaeseArr中
        int count = 0; //用于记录这是第几个非0数据
        for (int i = 0;i < 11;i++){
            for (int j = 0;j < 11;j++){
                if (chessArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }

            }
        }

        //输出稀疏数组
        System.out.println("稀疏数组为~~~~~~~~~");
        for (int i = 0;i < sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        /*
        * 稀疏数组转原始二维数组的思路：
        *  1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始二维数组
        *  2. 再读取稀疏数组后面的有效数据，并赋值给原始二维数组
        * */

        //1.先读取稀疏数组第一行，根据第一行数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //将有效数据存入二维数组
        for (int i = 1;i < sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出原始二维数组
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
