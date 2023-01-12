package com.sxq.sync;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/3 18:34
 * @description: TODO
 */
class Ticket{
    //票数
    private int number = 30;
    //操作方法 买票
    public synchronized void sale(){
        //判断：是否有票
        if(number > 0){
            System.out.println(Thread.currentThread().getName() + " : 卖出 : " + number-- + " 剩下 " + number);
        }
    }
}
public class SalTicket {
    //创建多个线程调用卖票方法

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();
    }
}
