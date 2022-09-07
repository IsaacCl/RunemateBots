package com.SmartAgility.Camera;


import com.runemate.game.api.hybrid.entities.details.Locatable;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;

import javax.annotation.Nullable;

public class OffsetLocatable implements Locatable {
    private Area area;
    private Coordinate coord;

    public OffsetLocatable(Locatable object, int xOffset, int yOffset) {
        Coordinate origCoordinate = object.getPosition();
        if (origCoordinate != null) {
            coord = new Coordinate(origCoordinate.getX() + xOffset, origCoordinate.getY() + yOffset, origCoordinate.getPlane());
            area = new Area.Absolute(coord);
        }
    }

    public boolean isValid() {
        return area != null && coord != null;
    }

    @Nullable
    @Override
    public Area.Rectangular getArea() {
        return area.toRectangular();
    }

    @Nullable
    @Override
    public Coordinate.HighPrecision getHighPrecisionPosition() {
        return null;
    }

    @Nullable
    @Override
    public Coordinate getPosition() {
        return coord;
    }
}
