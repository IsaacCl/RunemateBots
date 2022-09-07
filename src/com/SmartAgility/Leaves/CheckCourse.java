package com.SmartAgility.Leaves;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CheckCourse extends LeafTask {

    private final SmartAgility bot;

    public CheckCourse() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public void execute() {
        bot.guiData.currentTask = "Checking course";
        bot.courseList.checkCurrentCourse();
    }
}
