package com.idc130.scripts.mtaBot.leaves.graveyard;

import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CollectBones extends LeafTask {
    @Override
    public void execute() {
        var pileOfBones = GameObjects.newQuery().names("Bones").results().nearest();
        if (pileOfBones != null) {
            System.out.println("Grabbing bones");
            pileOfBones.interact("Grab");
        } else {
            System.out.println("Cannot find bones :'(");
        }
    }
}
