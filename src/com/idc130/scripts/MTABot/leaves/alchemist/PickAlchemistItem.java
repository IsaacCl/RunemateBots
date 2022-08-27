package com.idc130.scripts.MTABot.leaves.alchemist;

import com.idc130.scripts.MTABot.state.AlchemistGameState;
import com.runemate.game.api.hybrid.region.GameObjects;
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
            bestItemCupboard.click();
        }
    }
}
