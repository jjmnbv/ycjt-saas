package com.beitu.saas.common.utils;

import java.util.concurrent.*;

/**
 * @author xiaochong
 * @Description: 线程池工具类
 * @date 上午11:34 2017/9/27
 */
public class ThreadPoolUtils extends ThreadPoolExecutor{

    private static ExecutorService taskPool = Executors.newFixedThreadPool(20);


    private ThreadPoolUtils(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static ExecutorService getTaskInstance() {
        return taskPool;
    }

    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }
}
