package com.basejava.webapp;

public class MainDeadlock {
    public static final Object LOCK1 = new Object();
    public static final Object LOCK2 = new Object();

    public static final String THREAD1_NAME = "Thread1";
    public static final String THREAD2_NAME = "Thread2";
    public static final String LOCK1_NAME = "LOCK1";
    public static final String LOCK2_NAME = "LOCK2";


    public static void main(String[] args) {
        Thread1 thread1 = new Thread1(LOCK1, LOCK2, THREAD1_NAME, LOCK1_NAME, LOCK2_NAME);
        Thread1 thread2 = new Thread1(LOCK2, LOCK1, THREAD2_NAME, LOCK2_NAME, LOCK1_NAME);
        thread1.start();
        thread2.start();
    }
}

class Thread1 extends Thread {

    private final Object lock1;
    private final Object lock2;
    private final String threadName;
    private final String lock1Name;
    private final String lock2Name;

    public Thread1(Object lock1, Object lock2, String threadName, String lock1Name, String lock2Name) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.threadName = threadName;
        this.lock1Name = lock1Name;
        this.lock2Name = lock2Name;
    }

    public void run() {
        System.out.println(threadName + ": попытка захватить монитор объекта " + lock1Name);
        synchronized (lock1) {
            System.out.println(threadName + ": монитор объекта " + lock1Name + " захвачен");
            System.out.println(threadName + ": попытка захватить монитор объекта " + lock2Name);
            threadSleep();
            synchronized (lock2) {
                System.out.println(threadName + ": мониторы объектов " + lock1Name + " и " + lock2Name + " захвачены");
            }
        }
    }

    private void threadSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}