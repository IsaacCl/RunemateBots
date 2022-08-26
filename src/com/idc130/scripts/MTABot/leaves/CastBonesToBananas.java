package com.idc130.scripts.MTABot.leaves;

import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastBonesToBananas extends LeafTask {
    @Override
    public void execute() {
        System.out.println("Casting bones to banana");
        Magic.BONES_TO_BANANAS.activate();
    }
}
