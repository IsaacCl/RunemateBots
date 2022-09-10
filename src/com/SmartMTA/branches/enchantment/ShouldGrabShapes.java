package com.SmartMTA.branches.enchantment;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.SmartMTA.leaves.enchantment.CastEnchant;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldGrabShapes extends BranchTask {
    @Override
    public boolean validate() {
        return !Inventory.isFull();
    }

    @Override
    public TreeTask successTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3352, 9653, 0), new Coordinate(3353, 9652, 0)), "Cube Pile", "GameObject"));
    }

    @Override
    public TreeTask failureTask() {
        return new CastEnchant();
    }
}
