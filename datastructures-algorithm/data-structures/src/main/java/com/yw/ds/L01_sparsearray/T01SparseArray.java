package com.ds.L01_sparsearray;

import java.io.*;

/**
 * 原始数组
 * 0	0	0	0	0	0	0	0	0	0	0
 * 0	0	1	0	0	0	0	0	0	0	0
 * 0	0	0	2	0	0	0	0	0	0	0
 * 0	0	0	0	0	0	0	0	0	0	0
 * 0	0	0	0	0	0	0	0	0	0	0
 * 0	0	0	0	0	0	0	0	0	0	0
 * 0	0	0	0	0	0	0	0	0	0	0
 * 0	0	0	0	0	0	0	0	0	0	0
 * 0	0	0	0	0	0	0	0	0	0	0
 * 0	0	0	0	0	0	0	0	0	0	0
 * 0	0	0	0	0	0	0	0	0	0	0
 * <p>
 * 稀疏数组
 * 11	11	3
 * 1	2	1
 * 2	3	2
 * 4	5	1
 *
 * @author yangwei
 * @date 2020-05-02 22:22
 */
public class T01SparseArray {
    private static int[][] chessArr;
    private static final String FILENAME = "data-structures_algorithm/data-structures/resources/sparse.arr";

    public static void main(String[] args) throws IOException {
        // 1、初始化二维数组
        initOrgTwoDimArr();
        // 2、二维数组 转 稀疏数组
        int[][] sparseArr = twoDim2Sparse();
        // 3、稀疏数组写入文件
        writeArrToFile(sparseArr);
        // 4、将文件恢复成稀疏数组
        int[][] newSparseArr = readArrFromFile();
        // 5、稀疏数组 转 二维数组
        sparse2twoDim(newSparseArr);
    }

    /**
     * 1、初始化原始的二维数组
     */
    private static void initOrgTwoDimArr() {
        // 1、创建一个原始的二维数组 11 * 11
        // 0：表示没有值，1：黑子，2：蓝子
        chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 1;

        System.out.println("原始的二维数组：");
        printTwoDimArr(chessArr);
    }

    /**
     * 2、二维数组 转 稀疏数组
     */
    private static int[][] twoDim2Sparse() {
        // 2、将二维数组 转为 稀疏数组
        // 2.1、遍历二维数组
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        // 2.2、创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 2.3、遍历二维数组，将非0的值放入到sparseArr
        // 定义 count 用于记录是第几个非0数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        System.out.println("得到的稀疏数组：");
        printTwoDimArr(sparseArr);
        return sparseArr;
    }

    /**
     * 3、将稀疏数组 写入 文件 sparse.arr
     */
    private static void writeArrToFile(int[][] sparseArr) throws IOException {
        Writer out = new FileWriter(new File(FILENAME));
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < 3; j++) {
                out.write(sparseArr[i][j] + "\t");
            }
            out.write("\r\n");
        }
        System.out.println("保存文件成功");
        out.close();
    }

    /**
     * 4、将文件中的数据 恢复成 稀疏数组
     */
    private static int[][] readArrFromFile() throws IOException {
        FileReader fr = new FileReader(new File(FILENAME));
        BufferedReader br = new BufferedReader(fr);
        // 读取文件，看一下有多少行，以便初始化稀疏数组
        int line = 0;
        while (br.readLine() != null) {
            line++;
        }
        int[][] sparseArr = new int[line][3];

        // 再次读物每一行数据，拆分数据并给数组赋值
        fr = new FileReader(new File(FILENAME));
        br = new BufferedReader(fr);
        int count = 0;
        String s;
        while ((s = br.readLine()) != null) {
            String[] strArr = s.split("\t");
            sparseArr[count][0] = Integer.parseInt(strArr[0]);
            sparseArr[count][1] = Integer.parseInt(strArr[1]);
            sparseArr[count][2] = Integer.parseInt(strArr[2]);
            count++;
        }
        br.close();
        fr.close();
        System.out.println("从文件中恢复稀疏数组：");
        printTwoDimArr(sparseArr);
        return sparseArr;
    }

    /**
     * 5、稀疏数组 转 二维数组
     */
    private static void sparse2twoDim(int[][] sparseArr) {
        // 5、将稀疏数组 转为 原始的二维数组
        // 5.1、读取稀疏数组的第0行数据，创建原始的二维数组
        int[][] orgArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 5.2、遍历稀疏数组的第后几行数据，并赋值给原始的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            orgArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复得到二维数组：");
        printTwoDimArr(orgArr);
    }

    /**
     * 打印二维数组
     *
     * @param twoDimArr
     */
    private static void printTwoDimArr(int[][] twoDimArr) {
        for (int[] arr : twoDimArr) {
            for (int data : arr) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
