package com.sxq.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/7 14:50
 * @description: 两个线程实现对一个初始值0的变量  一个线程+1、一个线程-1 Lock实现
 */
//共享资源
class Share{
    private int number = 0;
    //创建Lock
    private final ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //+1操作
    public void incr() throws InterruptedException {
        //上锁
        lock.lock();
        try{
            //判断
            while (number != 0){
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + " :: " + number);
            //通知
            condition.signalAll();
        }finally {
            //解锁
            lock.unlock();
        }
    }
    //-1操作
    public void decr() throws InterruptedException {
        lock.lock();
        try{
            while (number != 1){
                condition.await();
            }

            number--;
            System.out.println(Thread.currentThread().getName() + " :: " + number);

            condition.signalAll();
        }finally {
            lock.unlock();
        }
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
