package com.SharedLibrary.UseItemOnObject;

import com.SharedLibrary.InteractObject.AtCorrectArea;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class UseMagicOnObject extends BranchTask {
    private final Magic spell;
    private final SmartObject object;

    public UseMagicOnObject(Magic spell, SmartObject object) {
        this.spell = spell;
        this.object = object;
    }

    @Override
    public boolean validate() {
        return spell.isSelected();
    }

    @Override
    public TreeTask successTask() {
        return new AtCorrectArea(object);
    }

    @Override
    public TreeTask failureTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                spell.activate();
            }
        };
    }
}
