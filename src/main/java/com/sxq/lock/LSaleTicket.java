package com.sxq.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/3 18:57
 * @description: TODO
 */
class LTicket{
    //票数
    private int number = 30;

    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock();
    //操作方法 卖票
    public void sale(){
        //上锁
        lock.lock();
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + " : 卖出 : " + number-- + " 剩下 " + number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //解锁
            lock.unlock();
        }

    }
}
public class LSaleTicket {
    public static void main(String[] args) {
        LTicket lTicket = new LTicket();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        },"CC").start();
    }
}
