package com.linsu.threaddemo;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        // 1、编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，每个线程将自己的ID在屏幕上打印10遍，要求输出结果必须按ABC的顺序显示；如：ABCABC….依次递推

        List<Thread> threads = new ArrayList<>(3);

        for(int i=0;i<3;i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {

                    for(int i=0;i<10;i++) {
                        System.out.println(Thread.currentThread().getId());
                    }
                }
            }));
        }

        for(int j=2;j>=0;j--){
            threads.get(j).start();
            threads.get(j).join();
        }

    }
}
