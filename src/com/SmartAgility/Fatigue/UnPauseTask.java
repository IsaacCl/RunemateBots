package com.SmartAgility.Fatigue;

import com.runemate.game.api.script.framework.AbstractBot;

import java.util.TimerTask;

public class UnPauseTask extends TimerTask {

    private boolean finished = false;

    private AbstractBot bot;

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
