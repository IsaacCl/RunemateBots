package com.SmartAgility.Camera;

import com.runemate.game.api.hybrid.entities.details.Locatable;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.util.calculations.Random;

public class CustomCamera {

    private final boolean lookHigh;
    private final double highNum = Random.nextDouble(0.8, 1);
    private int xOffset;
    private int yOffset;

    public CustomCamera(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.lookHigh = false;
    }

    public CustomCamera(boolean lookHigh) {
        this.lookHigh = lookHigh;
    }

    public CustomCamera() {
        xOffset = 0;
        yOffset = 0;
        lookHigh = false;
    }

    public void turnTo(Locatable object) {
        if (xOffset != 0 || yOffset != 0) {
            OffsetLocatable offsetLocatable = new OffsetLocatable(object, xOffset, yOffset);
            if (offsetLocatable.isValid()) {
                Camera.turnTo(offsetLocatable);
            } else {
                Camera.turnTo(object);
            }
        } else if (lookHigh) {
            Camera.turnTo(object, highNum);
        } else {
            Camera.turnTo(object);
        }
    }

    public void concurrentlyTurnTo(Locatable object) {
        if (xOffset != 0 || yOffset != 0) {
            OffsetLocatable offsetLocatable = new OffsetLocatable(object, xOffset, yOffset);
            if (offsetLocatable.isValid()) {
                Camera.concurrentlyTurnTo(offsetLocatable);
            } else {
                Camera.concurrentlyTurnTo(object);
            }
        } else if (lookHigh) {
            Camera.concurrentlyTurnTo(object, highNum);
        } else {
            Camera.concurrentlyTurnTo(object);
        }
    }

}
