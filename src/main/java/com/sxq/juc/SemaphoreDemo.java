package com.sxq.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/10 9:48
 * @description: SemaphoreDemo信号灯演示
 * 例子： 6辆汽车 停3个停车位
 */

public class SemaphoreDemo {

    public static void main(String[] args) {
        //创建Semaphore，设置许可证
        Semaphore semaphore = new Semaphore(3);
        
        //模拟6辆汽车
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                //抢占车位
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+ " 抢到了车位");
                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+ " ------离开了车位");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
