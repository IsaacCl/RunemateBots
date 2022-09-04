package com.SmartMTA.leaves.enchantment;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class PickUpShapes extends LeafTask {
    @Override
    public void execute() {
        Environment.getLogger().info("Pick up cubes");

        var cubePile = GameObjects.newQuery().names("Cube Pile").results().nearest();

        if (cubePile != null) {
            cubePile.click();
        }
    }
}
