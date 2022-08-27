package com.idc130.scripts.MTABot.leaves.alchemist;

import com.idc130.scripts.MTABot.state.AlchemistGameState;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GoToCupboard extends LeafTask {
    @Override
    public void execute() {
        var location = AlchemistGameState.getBestCupboardArea();

        final RegionPath path = RegionPath.buildTo(location);
        if(path != null)
        {
            System.out.println("Walking to cupboard " + AlchemistGameState.getBestItemLocationIndex());
            path.step(true);
        }
        else
        {
            System.out.println("Couldn't build a path to cupboard");
        }
    }
}
