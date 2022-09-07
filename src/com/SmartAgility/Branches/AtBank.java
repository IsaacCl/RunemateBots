package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.GoToBank;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AtBank extends BranchTask {

    private final SmartAgility bot;
    private final GoToBank goToBank = new GoToBank();
    private final NeedFoodBranch needFoodBranch = new NeedFoodBranch();

    public AtBank() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        return bot.courseList.getCurrentCourse().getBank().atBank();
    }

    @Override
    public TreeTask failureTask() {
        return goToBank;
    }

    @Override
    public TreeTask successTask() {
        return needFoodBranch;
    }
}
