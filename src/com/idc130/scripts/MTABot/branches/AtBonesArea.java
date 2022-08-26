package com.idc130.scripts.MTABot.branches;

import com.idc130.scripts.MTABot.leaves.CollectBones;
import com.idc130.scripts.MTABot.leaves.WalkToBonesArea;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



public class AtBonesArea extends BranchTask {

    private static final Area bonesArea = Area.rectangular(new Coordinate(3356, 9637, 1), new Coordinate(3352, 9635, 1));

    @Override
    public boolean validate() {
        return bonesArea.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return new IsBonesVisible();
    }

    @Override
    public TreeTask failureTask() {
        return new WalkToBonesArea();
    }
}
