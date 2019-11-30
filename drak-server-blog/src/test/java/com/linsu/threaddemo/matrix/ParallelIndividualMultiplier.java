package com.linsu.threaddemo.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 每个元素开启一个线程
 */
public class ParallelIndividualMultiplier {

    public static void multiply(int[][] matrix1, int[][] matrix2, int[][] result) {
        int rows1 = matrix1.length;
        int columns2 = matrix2[0].length;

        List<Thread> threads = new ArrayList<>();

        for(int i=0;i<rows1;i++){
            for(int j=0;j<columns2;j++){
                IndividualMultiplierTask task = new IndividualMultiplierTask(result, matrix1, matrix2, i, j);
                Thread thread = new Thread(task);
                thread.start();

                threads.add(thread);

                if(threads.size()%10==0){// 这里每10个执行完成之后再执行下一组
                    waitForThreads(threads);
                }
            }
        }
    }

    public static void waitForThreads(List<Thread> threads){
        for(Thread thread: threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        int[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        int[][] matrix2 = MatrixGenerator.generate(2000, 2000);
        int[][] result = new int[2000][2000];

        long start = System.currentTimeMillis();
        ParallelIndividualMultiplier.multiply(matrix1, matrix2, result);
        System.out.println("耗时:"+(System.currentTimeMillis()-start));
    }
}
