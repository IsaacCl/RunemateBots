package com.SmartMTA.leaves.lobby;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.LeafTask;

import static com.SmartMTA.utils.WalkingUtils.walkToDistantArea;

public class WalkToLobby extends LeafTask {
    private final Area lobbyArea = new Area.Rectangular(new Coordinate(3364, 3317, 0), new Coordinate(3362, 3319, 0));

    @Override
    public void execute() {
        walkToDistantArea(lobbyArea, "lobby");
    }
}
