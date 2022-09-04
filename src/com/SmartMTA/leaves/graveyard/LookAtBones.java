package com.SmartMTA.leaves.graveyard;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class LookAtBones extends LeafTask {

    @Override
    public void execute() {
        var pileOfBones = GameObjects.newQuery().names("Bones").results().nearest();

        if (pileOfBones != null) {
            Environment.getLogger().info("Looking at bones");
            Camera.turnTo(pileOfBones);
        } else {
            Environment.getLogger().info("Can't find bones to look at");
        }
    }
}
