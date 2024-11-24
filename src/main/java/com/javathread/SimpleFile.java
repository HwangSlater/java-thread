package com.javathread;

public class SimpleFile implements Runnable {
    private String name;
    private String type;
    private int size = 0;
    private int maxSize;

    public SimpleFile(String name, int maxSize, String type) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("fileSize는 0보다 커야합니다.");
        }

        this.name = name;
        this.type = type;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        System.out.println("다운로드 시작: " + name);

        while (size < maxSize) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("Thread.sleep도중 interrupt발생");
            }

            size++;
            System.out.println("다운로드 진행 사항: " + toString() + "/" + maxSize + "MB");
        }

        System.out.println("다운로드 완료: " + name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("'");
        sb.append(name).append(".").append(type);
        sb.append("', 크기: ");
        sb.append(size).append("MB");
        return sb.toString();
    }
}
