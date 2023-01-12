package com.sxq;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/3 18:13
 * @description: 用户线程和守护线程
 */

public class demo {
    public static void main(String[] args) {
        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa");
        //设置守护线程
        aa.setDaemon(true);
        aa.start();
        System.out.println(Thread.currentThread().getName()+" Over");
    }
}
