package com.sxq.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/8 9:34
 * @description: List集合线程不安全问题演示
 */

public class ThreadDemo4 {

    public static void main(String[] args) {
        //创建集合
//        List<String> list = new ArrayList<>();
        //Vector解决 不推荐
//        List<String> list = new Vector<>();
        //Collections解决
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        //CopyOnWriteArrayList 写时复制
//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                //向集合中添加内容
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }

        //演示HashSet
//        Set<String> set = new CopyOnWriteArraySet<>();
//
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                //向集合中添加内容
//                set.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(set);
//            },String.valueOf(i)).start();
//        }
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                //向集合中添加内容
                map.put(key,UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
