package com.javathread;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("워커 스레드 개수 입력: ");
        int workerCount = scanner.nextInt();

        System.out.print("작업 개수 입력: ");
        int taskCount = scanner.nextInt() - 1;

        // 랜덤한 작업 생성

        TaskScheduler scheduler = new TaskScheduler(taskCount);

    }
}