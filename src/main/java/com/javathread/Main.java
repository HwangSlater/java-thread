package com.javathread;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final int DEFAULT_MAX_FILE_INPUT = 5;
    private static final String[] fileType = { "mp4", "zip", "pdf" };
    private static int count = 0;
    private static ThreadPool threadPool = null;

    public static void main(String[] args) {
        int maxDownloads;

        System.out.print("최대 동시 다운로드 파일 수 입력: ");
        try {
            maxDownloads = scanner.nextInt();
            threadPool = new ThreadPool(maxDownloads);
        } catch (InputMismatchException e) {
            System.err.println("잘못된 형식입니다.");
        }

        while (count < DEFAULT_MAX_FILE_INPUT) {
            System.out.print("파일이름과 크기입력: (Ex: file1,13)");
            String[] fileInfo = scanner.next().split(",");

            String fileName = fileInfo[0];
            int fileSize = 0;
            try {
                fileSize = Integer.parseInt(fileInfo[1]);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 형식의 파일 사이즈입니다.");
            }

            Thread thread = new Thread(new SimpleFile(fileName, fileSize, fileType[random.nextInt(3)]));
            thread.start();
            threadPool.input(thread);

            count++;
        }

        threadPool.awaitCompletion();
        System.out.println("모든 다운로드가 완료되었습니다.");
    }
}