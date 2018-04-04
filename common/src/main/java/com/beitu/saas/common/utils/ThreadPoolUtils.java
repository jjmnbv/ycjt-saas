package com.beitu.saas.common.utils;

import java.util.concurrent.*;

/**
 * @author xiaochong
 * @Description: 线程池工具类
 * @date 上午11:34 2017/9/27
 */
public class ThreadPoolUtils extends ThreadPoolExecutor{

    private static ExecutorService taskPool = Executors.newFixedThreadPool(10);

    private static ExecutorService smsPool = Executors.newFixedThreadPool(10);


    private ThreadPoolUtils(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static ExecutorService getTaskInstance() {
        return taskPool;
    }

    public static ExecutorService getSmsInstance() {
        return smsPool;
    }

    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }
}
