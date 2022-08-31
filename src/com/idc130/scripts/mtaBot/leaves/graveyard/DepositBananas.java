package com.idc130.scripts.mtaBot.leaves.graveyard;

import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DepositBananas extends LeafTask {
    @Override
    public void execute() {
        //Deposit food chute
        var foodChute = GameObjects.newQuery().names("Food chute").results().nearest();
        if (foodChute != null) {
            System.out.println("Depositing bananas");
            foodChute.interact("Deposit");
        } else {
            System.out.println("Cannot find bananas");
        }
    }
}
