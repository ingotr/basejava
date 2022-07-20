package com.basejava.webapp;

public class MainDeadlock {
    public static final Object LOCK1 = new Object();
    public static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}

class Thread1 extends Thread {
    public void run() {
        System.out.println("Thread1: попытка захватить монитора объектак LOCK1");
        synchronized (MainDeadlock.LOCK1) {
            System.out.println("Thread1: монитор объекта LOCK1 захвачен");

            System.out.println("Thread1: попытка захватить монитора объектак LOCK2");
            synchronized (MainDeadlock.LOCK2) {
                System.out.println("Thread1: мониторы объектов LOCK1 и LOCK2 захвачены");
            }
        }
    }
}

class Thread2 extends Thread {
    public void run() {
        System.out.println("Thread2: попытка захватить монитора объектак LOCK2");
        synchronized (MainDeadlock.LOCK2) {
            System.out.println("Thread2: монитор объекта LOCK2 захвачен");

            System.out.println("Thread2: попытка захватить монитора объектак LOCK1");
            synchronized (MainDeadlock.LOCK1) {
                System.out.println("Thread2: мониторы объектов LOCK1 и LOCK2 захвачены");
            }
        }
    }
}