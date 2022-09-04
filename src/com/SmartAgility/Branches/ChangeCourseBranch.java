package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.CheckCourse;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ChangeCourseBranch extends BranchTask {

    private NeedBank needBank = new NeedBank();
    private CheckCourse checkCourse = new CheckCourse();

    private boolean firstTime = true;
    private boolean completedThisRound = false;

    private SmartAgility bot;
    public ChangeCourseBranch() { bot = (SmartAgility) Environment.getBot(); }

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
