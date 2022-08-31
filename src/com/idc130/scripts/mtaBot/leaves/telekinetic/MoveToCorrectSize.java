package com.idc130.scripts.mtaBot.leaves.telekinetic;

import com.idc130.scripts.mtaBot.utils.WalkingUtils;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class MoveToCorrectSize extends LeafTask {

    private final String correctSide;
    private final Area correctArea;

    public MoveToCorrectSize(Area correctArea, String correctSide) {
        this.correctSide = correctSide;
        this.correctArea = correctArea;
    }

    @Override
    public void execute() {
        System.out.println("Move to " + correctSide + " (" + correctArea + ")");

        WalkingUtils.walkToLocalArea(correctArea, "correctSide");
    }
}
