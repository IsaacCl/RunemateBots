package com.idc130.scripts.mtaBot.leaves.graveyard;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WalkToBonesArea extends LeafTask {
    private static final Area bonesArea = Area.rectangular(new Coordinate(3356, 9637, 1), new Coordinate(3352, 9635, 1));

    @Override
    public void execute() {
        final RegionPath path = RegionPath.buildTo(bonesArea);
        if (path != null) {
            System.out.println("Walking to bones area");
            path.step(true);
        } else {
            System.out.println("Couldn't build a path");
        }
    }
}
