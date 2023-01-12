package com.sxq.lock;

import javax.swing.*;
import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/7 15:29
 * @description: 线程间定制化通讯
 */

//资源类
class ShareResource {
    //定义标志位 1=AA 2=BB 3=CC
    private int flag = 1;
    //创建Lock锁
    private Lock lock = new ReentrantLock();

    //创建三个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //操作方法
    //打印5次，参数第几轮
    public void print5(int loop){
        //上锁
        lock.lock();
        try {
            //判断
            while (flag != 1){
                //等待
                c1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+" :: " + i + "轮数:" + loop);
            }
            //通知
            flag = 2; //先修改标志位
            c2.signal(); //通知BB线程
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    //打印10次，参数第几轮
    public void print10(int loop){
        //上锁
        lock.lock();
        try {
            //判断
            while (flag != 2){
                //等待
                c2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" :: " + i + "轮数:" + loop);
            }
            //通知
            flag = 3; //先修改标志位
            c3.signal(); //通知BB线程
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    //打印10次，参数第几轮
    public void print15(int loop){
        //上锁
        lock.lock();
        try {
            //判断
            while (flag != 3){
                //等待
                c3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+" :: " + i + "轮数:" + loop);
            }
            //通知
            flag = 1; //先修改标志位
            c1.signal(); //通知AA线程
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //释放锁
            lock.unlock();
        }
    }
}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5(i);
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10(i);
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15(i);
            }
        },"CC").start();
    }
}
