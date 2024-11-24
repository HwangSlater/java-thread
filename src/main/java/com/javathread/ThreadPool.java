package com.javathread;

import java.util.LinkedList;

public class ThreadPool {
    private final LinkedList<Thread> threadPoolList = new LinkedList<>();
    private final int maxSize;

    public ThreadPool(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("최대 동시 다운로드는 0보다 커야합니다.");
        }
        this.maxSize = maxSize;
    }

    // 스레드를 풀에 추가하는 메소드
    public synchronized void input(Thread thread) {
        // 풀에 스레드가 가득 찼으면 대기
        while (threadPoolList.size() >= maxSize) {
            try {
                wait(); // 공간이 생길 때까지 대기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 인터럽트 상태 복구
            }
        }

        threadPoolList.add(thread); // 스레드를 풀에 추가
        notifyAll(); // 다른 스레드에게 공간이 생겼음을 알림
    }

    public void awaitCompletion() {
        for (Thread thread : threadPoolList) {
            try {
                // 각 스레드가 종료될 때까지 기다립니다.
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("스레드가 종료될 때까지 기다리는 중 interrupt발생");
            }
        }
    }

}
