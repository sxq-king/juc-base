package com.sxq.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/10 14:32
 * @description: 阻塞队列
 */

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建一个阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        /**
         * 第一组
         */
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.add("d"));

//        true
//        true
//        true
//        a
//        Exception in thread "main" java.lang.IllegalStateException: Queue full
//        at java.util.AbstractQueue.add(AbstractQueue.java:98)
//        at java.util.concurrent.ArrayBlockingQueue.add(ArrayBlockingQueue.java:312)
//        at com.sxq.queue.BlockingQueueDemo.main(BlockingQueueDemo.java:22)

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

//        a
//        b
//        c
//        Exception in thread "main" java.util.NoSuchElementException
//            at java.util.AbstractQueue.remove(AbstractQueue.java:117)
//            at com.sxq.queue.BlockingQueueDemo.main(BlockingQueueDemo.java:37)

        /**
         * 第二组
         */
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("www"));

//        true
//        true
//        true
//        false


        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

//        a
//        b
//        c
//        null
        /**
         * 第三组
         */
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        /**
         * 第四组
         */
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d", 3L,TimeUnit.SECONDS ));
    }
}
