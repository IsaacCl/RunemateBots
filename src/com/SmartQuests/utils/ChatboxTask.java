package com.SmartQuests.utils;

public class ChatboxTask {

    private final TaskChecker taskChecker;
    private final String todo;

    public ChatboxTask(TaskChecker taskChecker, String todo) {
        this.taskChecker = taskChecker;
        this.todo = todo;
    }

    public String getTask(String text) {
        if (taskChecker.checkTask(text)) {
            return todo;
        } else {
            return null;
        }
    }
}

