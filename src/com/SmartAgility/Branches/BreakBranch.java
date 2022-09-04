package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.TakeABreak;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class BreakBranch extends BranchTask {

    private TakeABreak takeABreak = new TakeABreak();

    private ChangeCourseBranch changeCourseBranch = new ChangeCourseBranch();

    private SmartAgility bot;
    public BreakBranch() { bot = (SmartAgility) Environment.getBot(); }

    @Override
    public boolean validate() {
        return bot.fatigueHandler.timeForBreak();
    }

    @Override
    public TreeTask successTask() {
        return takeABreak;
    }

    @Override
    public TreeTask failureTask() {
        return changeCourseBranch;
    }
}
