package com.idc130.scripts.mtaBot.leaves.graveyard;

import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EnterCreatureGraveyard extends LeafTask {
    @Override
    public void execute() {
        var graveyardTeleport = GameObjects.newQuery().names("Graveyard Teleport").results().nearest();
        if (graveyardTeleport != null) {
            System.out.println("Goign to graveyard");
            graveyardTeleport.click();
        } else {
            System.out.println("Can't go to graveyard");
        }
    }
}
