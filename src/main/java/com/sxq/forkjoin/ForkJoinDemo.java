package com.sxq.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author song
 * @version 1.0
 * @date 2023/1/10 16:14
 * @description: TODO
 */
class MyTask extends RecursiveTask<Integer>{

    //拆分插值不能超过十
    private static final Integer VALUE = 10;
    private int begin;
    private int end;
    private int result;

    //创建一个有参构造
    public MyTask(int begin,int end){
        this.begin = begin;
        this.end = end;
    }

    //拆分和合并
    @Override
    protected Integer compute() {
        //判断相加的两个数差值是否大于十
        if((end - begin) < VALUE){
            //相加
            for(int i = begin; i <= end; i++){
                result = result + i;
            }
        }else {
            //进一步拆分
            //获取数据中间值
            int middle = (begin + end)/2;
            //拆分左边
            MyTask task01 = new MyTask(begin,middle);
            //拆分右边
            MyTask task02 = new MyTask(middle+1,end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask task = new MyTask(0, 100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(task);
        //获取最终合并之后的结果
        Integer integer = forkJoinTask.get();
        System.out.println(integer);
        //关闭分支合并池
        forkJoinPool.shutdown();
    }
}
