package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.TeleportSeers;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.AchievementDiary;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CanTeleport extends BranchTask {

    private SmartAgility bot;
    public CanTeleport() { bot = (SmartAgility) Environment.getBot(); }

    private Area teleportArea = new Area.Rectangular(new Coordinate(2704, 3465, 0), new Coordinate(2708, 3459, 0));
    private HealthBranch healthBranch = new HealthBranch();
    private TeleportSeers teleportSeers = new TeleportSeers();

    @Override
    public boolean validate() {
        InterfaceComponent component =  Magic.CAMELOT_TELEPORT.getComponent();

        return bot.guiData.UseSeersTeleport && teleportArea.contains(Players.getLocal()) &&
                ((component!=null && component.getSpriteId() == Magic.CAMELOT_TELEPORT.getSpriteIdWhenAvailable()) || Inventory.contains("Camelot teleport"));
    }

    @Override
    public TreeTask failureTask() {
        return healthBranch;
    }

    @Override
    public TreeTask successTask() {
        return teleportSeers;
    }
}