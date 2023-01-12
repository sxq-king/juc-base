package com.sxq.sync;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/7 10:57
 * @description: 两个线程实现对一个初始值0的变量  一个线程+1、一个线程-1 Synchronized实现
 */

//创建资源类
class Share{
    //创建资源
    private int number = 0;

    //+1的方法
    public synchronized void incr() throws InterruptedException {
        //判断 干活 通知
        while (number != 0){
            //判断number值是否是0，如果不是o，等待
            this.wait();
        }
        //如果number是0 干活
        number++;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        //通知 唤醒等待的线程
        this.notifyAll();
    }

    //-1的方法
    public synchronized void decr() throws InterruptedException {
        //判断 干活 通知
        while (number != 1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        //通知 唤醒等待的线程
        this.notifyAll();
    }
}
public class ThreadDemo1 {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"DD").start();
    }
}
