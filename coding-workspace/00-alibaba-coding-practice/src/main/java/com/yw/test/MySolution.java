package com.yw.test;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 设计三个线程，一个线程只能输出1 一个线程只能输出2 一个线程只能输出3 。
 * 要求：输出三个文件，内容分别是 123123123123.... 321321321... 312312312...
 * 每个文件输出各自规范的数字 10 组
 *
 * @author yangwei
 * @date 2020-01-31 19:21
 */
public class MySolution {
    private static final int NUM = 30;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        new MySolution().execute();
    }

    private void execute() throws Exception {
        ExecutorService executor = newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(NUM);

        StringBuffer sb1 = new StringBuffer(NUM);
        executor.execute(new Print1Task(sb1, latch, 0));
        executor.execute(new Print2Task(sb1, latch, 2));
        executor.execute(new Print3Task(sb1, latch, 1));
        latch.await();
        print123(sb1.toString());

        latch = new CountDownLatch(NUM);
        StringBuffer sb2 = new StringBuffer(NUM);
        executor.execute(new Print1Task(sb2, latch, 1));
        executor.execute(new Print2Task(sb2, latch, 2));
        executor.execute(new Print3Task(sb2, latch, 0));
        latch.await();
        print321(sb2.toString());

        latch = new CountDownLatch(NUM);
        StringBuffer sb3 = new StringBuffer(NUM);
        executor.execute(new Print1Task(sb3, latch, 2));
        executor.execute(new Print2Task(sb3, latch, 1));
        executor.execute(new Print3Task(sb3, latch, 0));
        latch.await();
        print312(sb3.toString());

        executor.shutdownNow();
    }

    private static void print123(String s) throws Exception {
        FileWriter fw = new FileWriter(new File("123.txt"));
        print(fw, s);
    }

    private static void print321(String s) throws Exception {
        FileWriter fw = new FileWriter(new File("321.txt"));
        print(fw, s);
    }

    private static void print312(String s) throws Exception {
        FileWriter fw = new FileWriter(new File("312.txt"));
        print(fw, s);
    }

    private static void print(FileWriter fw, String s) throws Exception {
        if (fw == null) {
            return;
        }
        try {
            fw.write(s);
        } finally {
            fw.close();
        }
    }

    class Print1Task extends AbstractPrintTask {
        public Print1Task(StringBuffer stringBuffer, CountDownLatch latch, int mod) {
            super(stringBuffer, latch, mod);
        }

        @Override
        void append() {
            this.stringBuffer.append("1");
        }
    }

    class Print2Task extends AbstractPrintTask {
        public Print2Task(StringBuffer stringBuffer, CountDownLatch latch, int mod) {
            super(stringBuffer, latch, mod);
        }

        @Override
        void append() {
            this.stringBuffer.append("2");
        }
    }

    class Print3Task extends AbstractPrintTask {
        public Print3Task(StringBuffer stringBuffer, CountDownLatch latch, int mod) {
            super(stringBuffer, latch, mod);
        }

        @Override
        void append() {
            this.stringBuffer.append("3");
        }
    }

    abstract class AbstractPrintTask implements Runnable {
        protected StringBuffer stringBuffer;
        protected CountDownLatch latch;
        protected int mod;

        AbstractPrintTask(StringBuffer stringBuffer, CountDownLatch latch, int mod) {
            this.stringBuffer = stringBuffer;
            this.latch = latch;
            this.mod = mod;
        }

        @Override
        public void run() {
            while (latch.getCount() > 0) {
                try {
                    if (LOCK.tryLock(1, TimeUnit.SECONDS) && latch.getCount() % 3 == mod) {
                        if (latch.getCount() > 0) {
                            append();
                        }
                        latch.countDown();
                    }
                } catch (Exception e) {
                } finally {
                    LOCK.unlock();
                }
            }
        }

        abstract void append();
    }
}

