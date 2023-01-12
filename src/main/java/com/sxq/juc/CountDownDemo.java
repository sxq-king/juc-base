package com.sxq.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/10 9:25
 * @description: 演示CountDownLatch
 * 例子：6个同学陆续离开教室之后，班长锁门
 */

public class CountDownDemo {
    public static void main(String[] args) throws InterruptedException {
        //问题演示
//        for (int i = 0; i < 6; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName() + "号同学离开教室");
//            },String.valueOf(i)).start();
//        }
//        System.out.println(Thread.currentThread().getName() + "班长锁门");
        /*
            0号同学离开教室
            3号同学离开教室
            5号同学离开教室
            2号同学离开教室
            4号同学离开教室
            main班长锁门
            1号同学离开教室
         */

        //解决方式 CountDownLatch

        //创建CountDownLatch对象，设置初始值
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "号同学离开教室");
                //计数-1
                latch.countDown();
            },String.valueOf(i)).start();
        }
        //等待
        latch.await();
        System.out.println(Thread.currentThread().getName() + "班长锁门");
    }
}
