package com.javathread;

public class Task {
    private int id;
    private int executionTime;
    private int priority;

    public Task(int id, int executionTime, int priority) {
        if (id <= 0 || executionTime <= 0 || priority <= 0) {
            throw new IllegalArgumentException("id, executionTime, priority는 0보다 커야합니다.");
        }

        this.id = id;
        this.executionTime = executionTime;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getPriority() {
        return priority;
    }
}
