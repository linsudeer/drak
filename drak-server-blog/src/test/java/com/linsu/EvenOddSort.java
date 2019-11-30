package com.linsu;

import java.util.Scanner;

/**
 * 实现奇数偶数区分
 */
public class EvenOddSort {

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入数组的长度：");
        int length = scanner.nextInt();
        int[] array = new int[length];

        int i=0;
        while(i<length) {
            System.out.print("请输入第"+(i+1)+"个数组元素：");
            int sc = scanner.nextInt();
            array[i] = sc;
            i++;
        }
        sortArray(array);
    }

    /**
     * 目的：排序，实现奇数在数组左边，偶数在数组右边
     * @param array
     */
    private static void sortArray(int[] array){
        if(array.length == 0)
            System.out.println("数组元素为空！");
        int m = 0, n = array.length-1; // m存放奇数在数组中的下标 n存放偶数在数组中的下标
        int temp; // 存放交换位置的临时数据
        int num = 0; // 计数器
        while(m!=n) {
            if(array[m]%2==1){//奇数
                m++;
            }else {//偶数
                temp = array[m];
                array[m] = array[n];
                array[n] = temp;
                n--;
            }
            num++;
        }
        System.out.println("共查找数组元素("+(num-1)+")次，排序后的数组为：");
        print(array);
    }

    /**
     * 打印数组元素
     * @param array
     */
    private static void print(int array[]) {
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }

}
