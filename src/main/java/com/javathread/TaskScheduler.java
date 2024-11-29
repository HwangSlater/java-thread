package com.javathread;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class TaskScheduler {
    private static final Random random = new Random();
    private static final Map<Integer, LinkedList<Task>> taskMap = new HashMap<>();
    private static final Map<Integer, Boolean> idMap = new HashMap<>();

    private static int highestPriority = 1;

    // 생성자에서 랜덤한 작업 생성
    public TaskScheduler(int taskCount) {
        int count = 0;
        while (count < taskCount) {
            Task task = new Task(random.nextInt(10), random.nextInt(10) * 1000, random.nextInt(5));

            if (Boolean.TRUE.equals(addTask(task))) {
                count++;
            }
        }
    }

    // 작업을 가지고 옴
    public synchronized Task getTask() {
        // 모든 큐가 비어있거나 가장 높은 우선순위 큐가 비어있을 경우 우선순위를 감소시킴
        while (taskMap.isEmpty() || taskMap.get(highestPriority).isEmpty()) {
            if (highestPriority > 0) {
                highestPriority--;
            } else {
                return null; // 모든 큐가 비었거나 더 이상 높은 우선순위의 작업이 없음
            }
        }

        return taskMap.get(highestPriority).poll();
    }

    // 같은 id를 가지는 task가 있다면 큐에 안들어가고 return false
    public synchronized Boolean addTask(Task task) {
        if (!idMap.containsKey(task.getId())) {
            idMap.put(task.getId(), true);

            taskMap.computeIfAbsent(task.getPriority(), k -> new LinkedList<>()).add(task);

            if (task.getPriority() > highestPriority) {
                highestPriority = task.getPriority();
            }

            return true;
        }

        System.out.println("Task with ID " + task.getId() + " already exists.");
        return false;
    }
}
