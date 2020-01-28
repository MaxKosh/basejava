package com.maxkosh.webapp;

public class DeadLockMain {
    public static void main(String[] args) {
        DeadLockMain firstLock = new DeadLockMain();
        DeadLockMain secondLock = new DeadLockMain();

        deadLock(firstLock, secondLock);
        deadLock(secondLock, firstLock);
    }

    private static void deadLock(DeadLockMain lock1, DeadLockMain lock2) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " hold this lock");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting for here...");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " locked firstLock & secondLock");
                }
            }
        }).start();
    }
}
