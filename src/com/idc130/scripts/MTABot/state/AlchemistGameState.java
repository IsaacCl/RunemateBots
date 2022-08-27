package com.idc130.scripts.MTABot.state;

import com.runemate.game.api.hybrid.entities.details.Locatable;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AlchemistGameState {
    public static final List<String> itemsList = Arrays.asList("Leather boots", "Adamant kiteshield", "Adamant med helm", "Emerald", "Rune longsword");

    private static final List<Coordinate> coordinates = Arrays.asList( new Coordinate(3360,9632,2),new Coordinate(3360,9636,2),new Coordinate(3360,9640,2),new Coordinate(3360,9644,2),
            new Coordinate(3369,9644,2),new Coordinate(3369,9640,2), new Coordinate(3369,9636,2), new Coordinate(3369,9630,2));


    private static final int[] itemInterfaceIds = {12713996, 12713997, 12713998, 12713999, 12714000};

    private static final int pizazzPointsInterfaceId = 12713995;

    private static Area getAreaFromCoordinate(Coordinate coordinate)
    {
        if(coordinate.getX() == 3360)
        {
            return new Area.Rectangular(new Coordinate(coordinate.getX()+1, coordinate.getY()+1, coordinate.getPlane()),
                    new Coordinate(coordinate.getX()+1, coordinate.getY()-1, coordinate.getPlane()));
        }
        else
        {
            return new Area.Rectangular(new Coordinate(coordinate.getX()-1, coordinate.getY()+1, coordinate.getPlane()),
                    new Coordinate(coordinate.getX()-1, coordinate.getY()-1, coordinate.getPlane()));
        }
    }

    private static Area getAreaFromCoordinate2(Coordinate coordinate)
    {
        if(coordinate.getX() == 3360)
        {
            return new Area.Rectangular(new Coordinate(coordinate.getX()+1, coordinate.getY()+1, coordinate.getPlane()),
                    new Coordinate(coordinate.getX(), coordinate.getY()-1, coordinate.getPlane()));
        }
        else
        {
            return new Area.Rectangular(new Coordinate(coordinate.getX(), coordinate.getY()+1, coordinate.getPlane()),
                    new Coordinate(coordinate.getX()-1, coordinate.getY()-1, coordinate.getPlane()));
        }
    }


    public static String[] getItemsArray() {
        return itemsList.toArray(new String[0]);
    }

    private static int getMostPointsIndex()
    {
        for (var i = 0; i < itemInterfaceIds.length; i++) {
            var pointsInterface = Interfaces.newQuery().ids(itemInterfaceIds[i]).results().first();

            if(pointsInterface!=null && Objects.equals(pointsInterface.getText(), "30"))
            {
                return i;
            }
        }

        return -1;
    }

    public static String getPizzazPoints()
    {
        var pointsInterface = Interfaces.newQuery().ids(pizazzPointsInterfaceId).results().first();

        if(pointsInterface!=null) return pointsInterface.getText();

        return "";
    }

    public static String getBestItem()
    {
        int mostPointsIndex = getMostPointsIndex();

        if(mostPointsIndex == -1) return "";

        return itemsList.get(mostPointsIndex);
    }

    public static Coordinate getBestItemLocation()
    {
        return coordinates.get(getBestItemLocationIndex());
    }

    public static int getBestItemLocationIndex()
    {
        if(!Objects.equals(lastBestItem, getBestItem()))
        {
            return itemsList.indexOf(getBestItem());
        }
        lastBestItem = getBestItem();
        if(nextToTry != -1) return nextToTry;
        return (itemsList.indexOf(getBestItem())+order)%coordinates.size();
    }

    public static Area getBestCupboardArea()
    {
        return getAreaFromCoordinate(getBestItemLocation());
    }
    public static Area getBestCupboardArea2()
    {
        return getAreaFromCoordinate2(getBestItemLocation());
    }

    private static int getOrderFromLocationAndItemIndices(int locationIndex, int itemIndex)
    {
        int order = locationIndex - itemIndex;
        if(order >= 0)
        {
            return order;
        }
        else
        {
            return order + coordinates.size();
        }
    }

    public static void printOrder() {
        String[] printedOrder = new String[] {"none","none","none","none","none","none","none","none"};

        for(int i=order; i<order+itemsList.size(); i++)
        {
            printedOrder[i%8] = itemsList.get(i-order);
        }

        System.out.println(Arrays.toString(printedOrder));
    }

    private static int order = 0;
    private static int nextToTry = -1;

    private static String lastBestItem = "";

    public static void justPickedUp(String item) {
        var player = Players.getLocal();

        if(player!=null)
        {
            var nearestCupboard = coordinates.stream().min((coord1, coord2) -> (int)(player.distanceTo(coord1) - player.distanceTo(coord2)));

            if(nearestCupboard.isPresent())
            {
                int cupboardIndex = coordinates.indexOf(nearestCupboard.get());
                int itemIndex = itemsList.indexOf(item);

                if(Objects.equals(item, ""))
                {
                    lastBestItem = "";
                    if(nextToTry == -1)
                    {
                        nextToTry = (cupboardIndex+2)%coordinates.size();
                        System.out.printf("Because picked up none at cupboard %x, try cupboard %x", cupboardIndex, nextToTry);
                    }
                    else if(nextToTry == cupboardIndex)
                    {
                        nextToTry = -1;
                        order = getOrderFromLocationAndItemIndices(cupboardIndex, 7);
                        printOrder();
                        System.out.printf("Because picked up none at cupboard %x, then %s must be at position %x, so going to position %x to get %s", cupboardIndex, itemsList.get(0), order, getBestItemLocationIndex(), getBestItem());
                    }
                }
                else
                {
                    nextToTry = -1;
                    order = getOrderFromLocationAndItemIndices(cupboardIndex, itemIndex);
                    printOrder();
                    System.out.printf("Because picked up %s at cupboard %x, then %s must be at position %x, so going to position %x to get %s", item, cupboardIndex, itemsList.get(0), order, getBestItemLocationIndex(), getBestItem());
                }
            }
        }
    }
}
