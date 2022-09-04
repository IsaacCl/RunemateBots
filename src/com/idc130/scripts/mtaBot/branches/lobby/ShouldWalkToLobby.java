package com.idc130.scripts.mtaBot.branches.lobby;

import com.idc130.scripts.mtaBot.leaves.lobby.WalkToLobby;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldWalkToLobby extends BranchTask {

    private final Area lobbyArea = new Area.Rectangular(new Coordinate(3361, 3320, 0), new Coordinate(3365, 3316, 0));

    private final BranchTask lobbyToJoin;

    public ShouldWalkToLobby(BranchTask lobbyToJoin) {
        this.lobbyToJoin = lobbyToJoin;
    }

    @Override
    public boolean validate() {
        var numGuardians = Npcs.newQuery().names("Enchantment Guardian", "Maze Guardian", "Telekinetic Guardian", "Graveyard Guardian", "Alchemy Guardian", "Telekinetic Guardian").results().size();

        if (lobbyArea.contains(Players.getLocal())) {
            return false;
        }

        if (numGuardians != 0)
            return false;
        var numOtherObjects = GameObjects.newQuery().names("Exit Teleport", "Cube Pile", "Coin Collector").results().size();
        return numOtherObjects == 0;
    }

    @Override
    public TreeTask successTask() {
        return new WalkToLobby();
    }

    @Override
    public TreeTask failureTask() {
        return lobbyToJoin;
    }
}
