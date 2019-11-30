package com.linsu;

public class Test {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("执行线程。。。"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(10000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        System.out.println("执行线程1。。。"+Thread.currentThread().getStackTrace().toString());

        new Thread(new MyRunnable()).start();
    }


}
