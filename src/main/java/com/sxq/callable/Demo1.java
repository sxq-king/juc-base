package com.sxq.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/8 17:41
 * @description: 比较Callable和Runnable
 */
//实现Runnable接口
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}
//实现Callable接口
class MyThread2 implements Callable{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " come in callable");
        return 200;
    }
}
public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable 创建线程
//        new Thread(new MyThread1(),"AA").start();

        //Callable 创建线程需要 借助FutureTask
        //FutureTask 创建方式一
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        //lambda表达式 创建方式二
        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName() + " come in callable");
            return 1024;
        });
        //FutureTask原理 未来任务 单开一个线程去做要做的任务

        //创建一个线程
        new Thread(futureTask2,"lucy").start();
        new Thread(futureTask1,"marry").start();
//        while (!futureTask2.isDone()){
//            System.out.println("wait......");
//        }
        //调用FutureTask中的get方法
        System.out.println(futureTask2.get());
        System.out.println(futureTask1.get());

        System.out.println(Thread.currentThread().getName() + " come over");
    }
}
