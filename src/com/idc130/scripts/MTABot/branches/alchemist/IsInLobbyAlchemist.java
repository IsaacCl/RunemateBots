package com.idc130.scripts.MTABot.branches.alchemist;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInLobbyAlchemist extends BranchTask {
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask successTask() {
        return null;
    }

    @Override
    public TreeTask failureTask() {
        return null;
    }
}
