package com.linsu.threaddemo.matrix;

public class MatrixUtil {
    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;// 因为矩阵列都一样
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++) {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.print("\n");
        }
    }
}
