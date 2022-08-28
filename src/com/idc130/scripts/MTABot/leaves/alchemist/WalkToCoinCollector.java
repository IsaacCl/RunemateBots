package com.idc130.scripts.MTABot.leaves.alchemist;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WalkToCoinCollector extends LeafTask {
    private static final Area coinCollectorArea = Area.rectangular(new Coordinate(3366, 9649, 2), new Coordinate(3363, 9648, 2));

    @Override
    public void execute() {
        final RegionPath path = RegionPath.buildTo(coinCollectorArea);
        if(path != null)
        {
            System.out.println("Walking to coin collector");
            path.step(true);
        }
        else
        {
            System.out.println("Couldn't build a path");
        }

    }
}
