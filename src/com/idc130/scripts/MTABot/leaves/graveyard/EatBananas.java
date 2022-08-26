package com.idc130.scripts.MTABot.leaves.graveyard;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EatBananas extends LeafTask {
    @Override
    public void execute() {
        var banana = Inventory.getItems("Banana").first();
        if(banana != null)
        {
            System.out.println("Eating banana");
            banana.click();
        }
        else
        {
            System.out.println("Can't find banana");
        }
    }
}
