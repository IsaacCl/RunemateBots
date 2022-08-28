package com.idc130.scripts.MTABot.leaves.alchemist;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import static com.idc130.scripts.MTABot.state.AlchemistGameState.getBestItemLocation;
import static com.idc130.scripts.MTABot.state.AlchemistGameState.getBestItemLocationIndex;

public class PickAlchemistItem extends LeafTask {
    @Override
    public void execute() {
        var bestItemLocation = getBestItemLocation();

        var bestItemCupboard = GameObjects.newQuery().names("Cupboard").on(bestItemLocation).results().first();

        System.out.println("Picking best item" + getBestItemLocationIndex() + " at cupboard " + bestItemCupboard +" at location " + bestItemLocation);

        if(bestItemCupboard != null)
        {
            var previousEmptySlots = Inventory.getEmptySlots();

            bestItemCupboard.click();

            Execution.delayUntil(() -> Inventory.getEmptySlots() < previousEmptySlots, 250, 1000);
        }
    }
}
