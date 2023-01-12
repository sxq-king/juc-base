package com.sxq.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/10 15:11
 * @description: 线程池常用分类演示
 */

public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //一池5线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5); //5个窗口
        //一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor(); //一个窗口
        //一池可扩容的线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        //10个顾客
        try {
            for (int i = 1; i <= 10; i++) {
                //执行
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            threadPool3.shutdown();
        }

    }
}
