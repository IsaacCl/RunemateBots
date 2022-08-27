package com.idc130.scripts.MTABot.leaves.alchemist;

import com.idc130.scripts.MTABot.state.AlchemistGameState;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

import static com.idc130.scripts.MTABot.state.AlchemistGameState.getBestItemLocation;
import static com.idc130.scripts.MTABot.state.AlchemistGameState.getBestItemLocationIndex;

public class PickAlchemistItem extends LeafTask {
    @Override
    public void execute() {

        System.out.println("Picking best item");

        var bestItemLocation = getBestItemLocation();
        var bestItemLocationIndex = getBestItemLocationIndex();

        var bestItemCupboard = GameObjects.newQuery().names("Cupboard").on(bestItemLocation).results().first();

        if(bestItemCupboard != null)
        {
            bestItemCupboard.click();
        }
    }
}
