package com.SmartAgility.Leaves;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GoToBank extends LeafTask {

    private final SmartAgility bot;

    public GoToBank() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public void execute() {
        bot.guiData.currentTask = "Going to bank.";

        bot.courseList.getCurrentCourse().getBank().goToBank();
    }
}
