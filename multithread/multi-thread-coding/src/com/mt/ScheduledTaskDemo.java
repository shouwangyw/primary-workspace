package com.mt;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangwei
 * @date 2020-05-15 15:33
 */
public class ScheduledTaskDemo {
    public static void main(String[] args) {
        new Timer("timer").schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer 执行任务~~");
            }
        }, 1000L, 1000L);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName() + "执行任务~~"),
                1000L, 5000L, TimeUnit.MILLISECONDS);
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
        }
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName() + "执行任务2~~"),
                1000L, 10000L, TimeUnit.MILLISECONDS);

    }
}
