package com.SmartAgility.Leaves;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class NextObstacle extends LeafTask {

    private final SmartAgility bot;

    public NextObstacle() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public void execute() {
        bot.getLogger().debug("Doing next obstacle.");
        bot.guiData.currentTask = "Doing next obstacle.";
        bot.courseList.doNextObstacle();
    }
}
