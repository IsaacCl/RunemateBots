package com.SmartMTA.branches.alchemist;

import com.SmartMTA.leaves.alchemist.TeleportToAlchemist;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInLobbyAlchemist extends BranchTask {

    @Override
    public boolean validate() {
        var portal = GameObjects.newQuery().names("Alchemists Teleport").results().nearest();
        return portal != null;
    }

    @Override
    public TreeTask successTask() {
        return new TeleportToAlchemist();
    }

    @Override
    public TreeTask failureTask() {
        return new HasAlchemistItem();
    }
}
