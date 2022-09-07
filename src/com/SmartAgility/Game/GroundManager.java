package com.SmartAgility.Game;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.Execution;

public class GroundManager {

    private final SmartAgility bot;
    private int failTimes = 0;

    public GroundManager(SmartAgility bot) {
        this.bot = bot;
    }

    public boolean checkGroundAt(SmartAgility bot) {
        GroundItem mark = GroundItems.newQuery().names("Mark of grace").results().nearest();

        if (mark != null && mark.getPosition() != null && bot.courseList.isPlayerInSameArea(mark) &&
                (!Inventory.isFull() || Inventory.contains("Mark of grace"))) {
            bot.getLogger().debug("Found item " + mark);
            return true;
        } else {
            return false;
        }
    }

    public void collectItems() {
        bot.getLogger().debug("Collecting Items");
        GroundItem mark = GroundItems.newQuery().names("Mark of grace").results().nearest();
        if (mark != null) {

            if (!mark.isVisible() || (failTimes > 9 && failTimes % 5 == 0)) {
                Camera.turnTo(mark);

                RegionPath path = RegionPath.buildTo(mark);
                if (path != null) {
                    path.step();
                } else {
                    var web = Traversal.getDefaultWeb();

                    if (web != null) {
                        WebPath path2 = Traversal.getDefaultWeb().getPathBuilder().buildTo(mark);
                        if (path2 != null) {
                            path2.step();
                        } else {
                            bot.getLogger().debug("Couldn't find path to mark!");
                        }
                    }
                }
            }

            if (mark.interact("Take")) {
                failTimes = 0;
                Execution.delayUntil(() -> !mark.isValid(), 500, 2000);
            } else {
                failTimes++;
                if (failTimes > 50) {
                    var bot = Environment.getBot();
                    if (bot != null) {
                        bot.stop("Couldn't pick up mark for some reason. Exiting.");
                    }
                }
            }
        }
    }
}
