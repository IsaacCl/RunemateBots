package com.SmartMTA.branches.graveyard;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.SharedLibrary.InteractObject.WaitingCondition;
import com.SmartMTA.leaves.graveyard.CastBonesToBananas;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasBones extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.getQuantity("Animals' bones") >= 8;
    }

    @Override
    public TreeTask successTask() {
        return new CastBonesToBananas();
    }

    @Override
    public TreeTask failureTask() {
        return new InteractObject(new SmartObject(Area.rectangular(new Coordinate(3356, 9637, 1), new Coordinate(3352, 9635, 1)), "Bones", "GameObject", new WaitingCondition(0, 100)));
    }
}
