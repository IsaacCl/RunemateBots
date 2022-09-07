package com.SmartAgility.Fatigue;

import com.SmartAgility.Helpers.CustomPlayerSense;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.util.Time;
import com.runemate.game.api.hybrid.util.calculations.Random;

import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class FatigueHandler {

    private final SmartAgility bot;
    private final Timer timer;
    private final FatigueStrategy breakStrategy;
    private UnPauseTask unpauseTask;
    private int nextBreak;

    public FatigueHandler(SmartAgility bot) {
        this.bot = bot;
        timer = new Timer();
        switch (CustomPlayerSense.Key.BREAK_STRATEGY.getAsInteger()) {
            case 2:
                breakStrategy = FatigueStrategy.MEDIUM;
                break;
            case 3:
                breakStrategy = FatigueStrategy.LONG;
                break;
            default:
                breakStrategy = FatigueStrategy.SHORT;
                break;
        }

        finishBreak();
    }

    public boolean timeForBreak() {
        if (bot.guiData.currentTime.getRuntime(TimeUnit.MINUTES) >= bot.guiData.MaxRuntime) {
            bot.stop("Exceeded maximum runtime");
        }

        return bot.guiData.UseBoredomSystem && (bot.guiData.currentTime.getRuntime(TimeUnit.MILLISECONDS) > nextBreak);
    }

    public void takeBreak() {

        int timeRightNow = (int) bot.guiData.currentTime.getRuntime(TimeUnit.MILLISECONDS);
        int breakTime = Random.nextInt(getMilliseconds(breakStrategy.minBreakTime), getMilliseconds(breakStrategy.maxBreakTime));

        breakFor((int) (breakTime * getBreakMultiplier(timeRightNow)));
    }

    public void finishBreak() {
        if (unpauseTask != null && !unpauseTask.isFinished()) {
            unpauseTask.cancel();
        }

        int intraBreakLength = Random.nextInt(getMilliseconds(breakStrategy.minIntraBreakTime), getMilliseconds(breakStrategy.maxIntraBreakTime));
        int timeRightNow = (int) bot.guiData.currentTime.getRuntime(TimeUnit.MILLISECONDS);

        nextBreak = timeRightNow + (int) (intraBreakLength * getIntraBreakMultiplier(timeRightNow));
    }

    private void breakFor(int milliseconds) {
        unpauseTask = new UnPauseTask(bot);
        timer.schedule(unpauseTask, milliseconds);
        bot.guiData.currentTask = "Un-pausing the bot at " + Time.format(bot.guiData.currentTime.getRuntime(TimeUnit.MILLISECONDS) + milliseconds);
        bot.pause("Taking a quick break for " + getMinutes(milliseconds) + " minutes.");
    }

    private int getMilliseconds(int minutes) {
        return minutes * 60 * 1000;
    }

    private int getMinutes(int milliseconds) {
        return milliseconds / 60000;
    }

    private double getBreakMultiplier(int milliseconds) {
        if (milliseconds < getMilliseconds(120)) {
            return 0.8;
        } else if (milliseconds < getMilliseconds(240)) {
            return 1;
        } else if (milliseconds < getMilliseconds(360)) {
            return 1.2;
        } else {
            return 1.4;
        }
    }

    private double getIntraBreakMultiplier(int milliseconds) {
        if (milliseconds < getMilliseconds(120)) {
            return 1.1;
        } else if (milliseconds < getMilliseconds(240)) {
            return 1;
        } else if (milliseconds < getMilliseconds(360)) {
            return 0.9;
        } else {
            return 0.8;
        }
    }

}
