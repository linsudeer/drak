package com.linsu.threaddemo.matrix;

public class IndividualMultiplierTask implements Runnable {

    private final int[][] result;
    private final int[][] matrix1;
    private final int[][] matrix2;

    private final int row;
    private final int columns;

    public IndividualMultiplierTask(int[][] result, int[][] matrix1, int[][] matrix2, int i, int j) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = i;
        this.columns = j;
    }

    @Override
    public void run() {
        result[row][columns] = 0;
        for(int k=0;k<matrix1[row].length;k++){
            result[row][columns] += matrix1[row][k]*matrix2[k][columns];
        }
    }
}
