package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.EmptyLeaf;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class RootBranch extends BranchTask {

    private final EmptyLeaf emptyLeaf = new EmptyLeaf();
    private final BreakBranch breakBranch = new BreakBranch();

    private final SmartAgility bot;

    public RootBranch() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        return bot.guiData.GUIWait;
    }

    @Override
    public TreeTask successTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask failureTask() {
        return breakBranch;
    }
}
