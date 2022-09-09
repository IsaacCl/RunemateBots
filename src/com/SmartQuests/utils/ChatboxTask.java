package com.SmartQuests.utils;

import com.runemate.game.api.script.framework.tree.TreeTask;

public class ChatboxTask {

    private final TaskChecker taskChecker;
    private final TreeTask todo;

    public ChatboxTask(TaskChecker taskChecker, TreeTask todo) {
        this.taskChecker = taskChecker;
        this.todo = todo;
    }

    public TreeTask getTask(String text) {
        if (taskChecker.checkTask(text)) {
            return todo;
        } else {
            return null;
        }
    }
}

