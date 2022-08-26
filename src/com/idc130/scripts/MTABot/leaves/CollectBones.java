package com.idc130.scripts.MTABot.leaves;

import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CollectBones extends LeafTask {
    public static final int PILE_OF_BONES_ID = 10727;

    @Override
    public void execute() {
        var pileOfBones = GameObjects.newQuery().names("Bones").results().nearest();
        if(pileOfBones != null) {
            System.out.println("Grabbing bones");
            pileOfBones.interact("Grab");
        } else {
            System.out.println("Cannot find bones :'(");
        }
    }
}
