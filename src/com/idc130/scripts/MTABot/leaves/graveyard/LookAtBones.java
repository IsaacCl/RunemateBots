package com.idc130.scripts.MTABot.leaves.graveyard;

import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class LookAtBones extends LeafTask {

    @Override
    public void execute() {
        var pileOfBones = GameObjects.newQuery().names("Bones").results().nearest();

        if(pileOfBones!=null)
        {
            System.out.println("Looking at bones");
            Camera.turnTo(pileOfBones);
        }
        else
        {
            System.out.println("Can't find bones to look at");
        }
    }
}
