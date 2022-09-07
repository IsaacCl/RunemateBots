package com.SmartAgility.Fatigue;

import com.runemate.game.api.script.framework.AbstractBot;

import java.util.TimerTask;

public class UnPauseTask extends TimerTask {

    private final AbstractBot bot;
    private boolean finished = false;

    public UnPauseTask(AbstractBot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        bot.resume();
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

}
