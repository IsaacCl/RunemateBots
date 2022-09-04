package com.SmartAgility.Game;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;

public class CustomBank {

    private SmartAgility bot;
    private Area bankLocation;

    private int stuckTimes = 0;

    public static boolean HasFood = true;
    public static boolean HasPotion = true;

    public CustomBank(SmartAgility bot, Area bankLocation)
    {
        this.bot = bot;
        this.bankLocation = bankLocation;
    }

    public boolean atBank()
    {
        return bankLocation.contains(Players.getLocal());
    }

    public void goToBank()
    {
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bankLocation);

        Player localPlayer = Players.getLocal();

        if(path != null && localPlayer != null)
        {
            if(path.step())
            {
                bot.getLogger().debug("Stepping towards area!");
                Execution.delayUntil(()->!localPlayer.isMoving(), 500);
                stuckTimes = 0;
            }
            else
            {
                stuckTimes++;
            }
        }
        else {
            bot.getLogger().debug("We're stuck boss!");
            if (stuckTimes > 10) {
                bot.stop("Can't find exit");
            }
            stuckTimes++;
        }
    }

}
