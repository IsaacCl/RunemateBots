package com.SmartAgility.Game;

import com.SmartAgility.Helpers.CustomPlayerSense;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.navigation.Traversal;


public class PlayerManager {

    private final int maxDamage = 8;

    private final SmartAgility bot;

    public PlayerManager(SmartAgility bot) {
        this.bot = bot;
    }

    public boolean hasLowHealth() {
        int currentHealth = Health.getCurrent();
        bot.getLogger().debug("Checking if current health: " + currentHealth + "<=" + bot.guiData.getFoodHealth());
        boolean hasLowHealth = (currentHealth <= bot.guiData.getFoodHealth());
        if (currentHealth <= 0) {
            return false;
        } else {
            if (!hasLowHealth) {
                bot.getLogger().debug("Has high health");
            }
            return hasLowHealth;
        }
    }

    public boolean hasLowEnergy() {
        int runEnergy = Traversal.getRunEnergy();
        if (runEnergy < 0) {
            return false;
        } else {
            return Traversal.getRunEnergy() < CustomPlayerSense.Key.DRINKING_ENERGY_BUFFER.getAsInteger();
        }
    }
}
