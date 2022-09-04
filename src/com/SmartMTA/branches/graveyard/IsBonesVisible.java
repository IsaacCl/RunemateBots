package com.SmartMTA.branches.graveyard;

import com.SmartMTA.leaves.graveyard.CollectBones;
import com.SmartMTA.leaves.graveyard.LookAtBones;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBonesVisible extends BranchTask {
    @Override
    public boolean validate() {
        var pileOfBones = GameObjects.newQuery().names("Bones").results().nearest();

        return pileOfBones != null && pileOfBones.isVisible();
    }

    @Override
    public TreeTask successTask() {
        return new CollectBones();
    }

    @Override
    public TreeTask failureTask() {
        return new LookAtBones();
    }
}