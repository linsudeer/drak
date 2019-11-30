package com.linsu.threaddemo.matrix;

import java.util.Random;

/**
 * 矩阵生成器
 */
public class MatrixGenerator {

    public static int[][] generate(int rows, int columns) {
        int[][] ret = new int[rows][columns];
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++){
                Random random = new Random();
                ret[i][j] = random.nextInt(10);
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        MatrixUtil.printMatrix(generate(9, 4));
    }
}
