package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.CheckCourse;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ChangeCourseBranch extends BranchTask {

    private final NeedBank needBank = new NeedBank();
    private final CheckCourse checkCourse = new CheckCourse();
    private final SmartAgility bot;
    private boolean firstTime = true;
    private boolean completedThisRound = false;

    public ChangeCourseBranch() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public TreeTask successTask() {
        firstTime = false;
        completedThisRound = true;
        return checkCourse;
    }

    @Override
    public TreeTask failureTask() {
        completedThisRound = false;
        return needBank;
    }

    @Override
    public boolean validate() {
        return firstTime || (!completedThisRound && bot.courseList.isPlayerInFirstArea());
    }
}
