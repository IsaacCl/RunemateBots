package com.SmartMTA.branches.graveyard;

import com.SmartMTA.leaves.graveyard.EnterCreatureGraveyard;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInLobbyGraveyard extends BranchTask {
    @Override
    public boolean validate() {
        var portal = GameObjects.newQuery().names("Graveyard Teleport").results().nearest();
        return portal != null;
    }

    @Override
    public TreeTask successTask() {
        return new EnterCreatureGraveyard();
    }

    @Override
    public TreeTask failureTask() {
        return new HasBananas();
    }
}
