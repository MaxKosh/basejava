package com.maxkosh.webapp;

public class DeadLockMain {
    public static void main(String[] args) {
        DeadLockMain firstLock = new DeadLockMain();
        DeadLockMain secondLock = new DeadLockMain();

        new Thread(() -> {
            synchronized (firstLock) {
                System.out.println(Thread.currentThread().getName() + " locked firstLock");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting for here...");
                synchronized (secondLock) {
                    System.out.println(Thread.currentThread().getName() + " locked firstLock & secondLock");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + " locked secondLock");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting for here...");
                synchronized (firstLock) {
                    System.out.println(Thread.currentThread().getName() + " locked secondLock & firstLock");
                }
            }
        }).start();
    }
}
