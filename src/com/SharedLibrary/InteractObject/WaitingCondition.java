package com.SharedLibrary.InteractObject;

import com.runemate.game.api.script.Execution;

import java.util.concurrent.Callable;

public class WaitingCondition {
    private final int minimumTime;
    private final int maximumTime;
    private Callable<Boolean> waitCondition = null;

    public WaitingCondition(Callable<Boolean> waitCondition, int minimumTime, int maximumTime) {
        this.waitCondition = waitCondition;
        this.minimumTime = minimumTime;
        this.maximumTime = maximumTime;
    }

    public WaitingCondition(int minimumTime, int maximumTime) {
        this.minimumTime = minimumTime;
        this.maximumTime = maximumTime;
    }

    public void delay() {
        if (waitCondition != null) {
            Execution.delayUntil(waitCondition, minimumTime, maximumTime);
        } else {
            Execution.delay(minimumTime, maximumTime);
        }
    }
}
