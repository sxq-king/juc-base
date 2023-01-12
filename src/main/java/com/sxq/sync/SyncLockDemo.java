package com.sxq.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/8 16:57
 * @description: 可重入锁
 */

public class SyncLockDemo {

    public synchronized void add(){
        add();
    }

    public static void main(String[] args) {

//        new SyncLockDemo().add();
//        Object o = new Object();
//        new Thread(()->{
//            synchronized (o){
//                System.out.println(Thread.currentThread().getName() + " 外层");
//                synchronized (o){
//                    System.out.println(Thread.currentThread().getName() + " 中层");
//                    synchronized (o){
//                        System.out.println(Thread.currentThread().getName() + " 内层");
//                    }
//                }
//            }
//        },"t1").start();
        //lock演示可重入锁
        ReentrantLock lock = new ReentrantLock();
        //创建线程
        new Thread(()->{
            try {
                //枷锁
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "外层");

                try {
                    //枷锁
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "内层");
                }finally {
                    //释放锁
//                    lock.unlock();
                }

            }finally {
                //释放锁
                lock.unlock();
            }
        },"lock").start();
    }
}
