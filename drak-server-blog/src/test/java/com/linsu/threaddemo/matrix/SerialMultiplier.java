package com.linsu.threaddemo.matrix;

import org.junit.Assert;

/**
 * 串行实现矩阵乘法 (3x4)*(4x7)=(3x7)
 * 通过三个嵌套循环实现
 */
public class SerialMultiplier {

    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;
        Assert.assertEquals("矩阵不能做乘法运算，请检查是否满足(axb)*(bxc)=(axc)格式", true, columns1==rows2);
        int[][] result = new int[rows1][columns2];
        for(int i=0;i<rows1;i++){
            for(int j=0;j<columns2;j++){
                result[i][j] = 0;
                for(int k=0;k<columns1;k++){
                    result[i][j] += matrix1[i][k]*matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        int[][] matrix2 = MatrixGenerator.generate(2000, 2000);
        long start = System.currentTimeMillis();
        int[][] result = multiply(matrix1, matrix2);
        System.out.println("耗时:"+(System.currentTimeMillis()-start));
//        MatrixUtil.printMatrix(result);
    }
}
