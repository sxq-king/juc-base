package com.sxq.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/10 9:37
 * @description: CyclicBarrier演示
 * 例子: 集齐7颗龙珠召唤神龙
 */

public class CyclicBarrierDemo {
    //创建固定值
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("集齐7颗龙珠就可以召唤神龙");
        });

        //集齐龙珠的过程
        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " 星龙珠");
                    //等待集齐七颗龙珠
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }
    }

}
