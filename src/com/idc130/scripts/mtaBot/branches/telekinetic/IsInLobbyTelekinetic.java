package com.idc130.scripts.mtaBot.branches.telekinetic;

import com.idc130.scripts.mtaBot.leaves.telekinetic.TeleportTelekinetic;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInLobbyTelekinetic extends BranchTask {
    @Override
    public boolean validate() {
        var portal = GameObjects.newQuery().names("Telekinetic Teleport").results().nearest();
        return portal != null;
    }

    @Override
    public TreeTask successTask() {
        return new TeleportTelekinetic();
    }

    @Override
    public TreeTask failureTask() {
        return new AtMaze();
    }
}
