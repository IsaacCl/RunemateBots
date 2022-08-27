package com.idc130.scripts.MTABot.leaves.alchemist;

import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TeleportToAlchemist extends LeafTask {
    @Override
    public void execute() {
        var alchemistsTeleport = GameObjects.newQuery().names("Alchemists Teleport").results().nearest();
        if(alchemistsTeleport != null)
        {
            System.out.println("Goign to alchemist");
            alchemistsTeleport.click();
        }
        else
        {
            System.out.println("Can't go to alchemist");
        }
    }
}
