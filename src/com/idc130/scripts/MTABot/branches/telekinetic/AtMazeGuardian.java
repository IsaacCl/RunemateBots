package com.idc130.scripts.MTABot.branches.telekinetic;

import com.idc130.scripts.MTABot.leaves.telekinetic.GoToMazeGuardian;
import com.idc130.scripts.MTABot.leaves.telekinetic.TalkToMazeGuardian;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AtMazeGuardian extends BranchTask {
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new TalkToMazeGuardian();
    }

    @Override
    public TreeTask failureTask() {
        return new GoToMazeGuardian();
    }
}
