package com.sxq.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/10 16:32
 * @description: 异步回调
 */

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+ " completableFuture1");
        });
        completableFuture1.get();

        //异步调用有返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+ " completableFuture2");
            //模拟异常
            int i = 1/0;
            return 1024;
        });
        completableFuture2.whenComplete((t,u)->{
            System.out.println("------t "+t);
            System.out.println("------u "+u); //异常信息
        }).get();

    }
}
