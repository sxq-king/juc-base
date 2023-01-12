package com.sxq.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/10 10:08
 * @description: 读写锁
 */
//创建资源类
class MyCache {
    //创建Map集合
    private volatile Map<String, Object> map = new HashMap<>();

    //创建读写锁
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    //放数据
    public void put (String key, Object value) throws InterruptedException {
        //加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写操作 " + key);
            //等一会
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + " 写完了 " + key);
        }catch (InterruptedException e ){
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    //取数据
    public Object get(String key) throws InterruptedException {
        //加读锁
        rwLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取操作 " + key);
            //等一会
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读完了 " + key);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
        return result;
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //放
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                try {
                    myCache.put(num+"",num+"");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }
        //取
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                try {
                    myCache.get(num+"");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }
        /*
        2 正在写操作 2
        2 写完了 2
        3 正在写操作 3
        3 写完了 3
        4 正在写操作 4
        4 写完了 4
        1 正在写操作 1
        1 写完了 1
        5 正在写操作 5
        5 写完了 5
        1 正在读取操作 1
        2 正在读取操作 2
        3 正在读取操作 3
        4 正在读取操作 4
        5 正在读取操作 5
        3 读完了 3
        2 读完了 2
        5 读完了 5
        4 读完了 4
        1 读完了 1
         */
    }
}
