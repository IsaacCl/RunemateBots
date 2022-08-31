package com.idc130.scripts.mtaBot.leaves.telekinetic;

import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TeleportTelekinetic extends LeafTask {
    @Override
    public void execute() {
        var alchemistsTeleport = GameObjects.newQuery().names("Telekinetic Teleport").results().nearest();
        if (alchemistsTeleport != null) {
            System.out.println("Going to telekinetic");
            alchemistsTeleport.click();
        } else {
            System.out.println("Can't go to telekinetic");
        }
    }
}
