package com.idc130.scripts.MTABot.state;

import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AlchemistGameState {
    public static final List<String> itemsList = Arrays.asList("Leather boots", "Adamant kiteshield", "Adamant med helm", "Emerald", "Rune longsword");

    private static final List<Coordinate> coordinates = Arrays.asList( new Coordinate(3360,9632,2),new Coordinate(3360,9636,2),new Coordinate(3360,9640,2),new Coordinate(3360,9644,2),
            new Coordinate(3369,9644,2),new Coordinate(3369,9640,2), new Coordinate(3369,9636,2), new Coordinate(3369,9632,2));


    private static final int[] itemInterfaceIds = {12713996, 12713997, 12713998, 12713999, 12714000};

//    private static final int pizazzPointsInterfaceId = 12713995;

    private static Area getAreaFromCoordinate(Coordinate coordinate)
    {
        if(coordinate.getX() == 3360)
        {
            return new Area.Absolute(new Coordinate(coordinate.getX()+1, coordinate.getY(), coordinate.getPlane()));
        }
        else {
            return new Area.Absolute(new Coordinate(coordinate.getX() - 1, coordinate.getY(), coordinate.getPlane()));
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

        return 0;
    }

//    public static String getPizzazPoints()
//    {
//        var pointsInterface = Interfaces.newQuery().ids(pizazzPointsInterfaceId).results().first();
//
//        if(pointsInterface!=null) return pointsInterface.getText();
//
//        return "";
//    }

    public static String getBestItem()
    {
        int mostPointsIndex = getMostPointsIndex();

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
            var cupboard = GameObjects.newQuery().names("Cupboard").results().nearest();

            if(cupboard == null)
            {
                System.out.println("Oops everything changed and there's no nearby cupboard so go to cupboard 0");
                return 0;
            }

            var nearestCupboard = cupboard.getPosition();
            var nearestCupboardIndex = coordinates.indexOf(nearestCupboard);
            System.out.println("Oops everything changed and so testing nearest cupboard " + nearestCupboardIndex);
            return nearestCupboardIndex;
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

//    public static void printOrder() {
//        String[] printedOrder = new String[] {"none","none","none","none","none","none","none","none"};
//
//        for(int i=order; i<order+itemsList.size(); i++)
//        {
//            printedOrder[i%8] = itemsList.get(i-order);
//        }
//
//        System.out.println(Arrays.toString(printedOrder));
//    }

    private static int order = 0;
    private static int nextToTry = -1;

    private static String lastBestItem = itemsList.get(0);


    public static void justPickedUp(String item) {

        System.out.println("Just picked up an item!");

        var player = Players.getLocal();

        if(player!=null)
        {
            var cupboard = GameObjects.newQuery().names("Cupboard").results().nearest();
            if(cupboard == null)
            {
                return;
            }

            var nearestCupboard = cupboard.getPosition();
            int cupboardIndex = coordinates.indexOf(nearestCupboard);
            int itemIndex = itemsList.indexOf(item);

            if(Objects.equals(item, ""))
            {
                if(nextToTry == -1)
                {
                    nextToTry = (cupboardIndex+2)%coordinates.size();
//                    System.out.printf("Because picked up none at cupboard %x, try cupboard %x", cupboardIndex, nextToTry);
//                    System.out.println();
                }
                else if(nextToTry == cupboardIndex)
                {
                    nextToTry = -1;
                    order = getOrderFromLocationAndItemIndices(cupboardIndex, 7);
//                    printOrder();
//                    System.out.printf("Going to position %x to get %s", getBestItemLocationIndex(), getBestItem());
//                    System.out.println();
                }
            }
            else
            {
                nextToTry = -1;
                order = getOrderFromLocationAndItemIndices(cupboardIndex, itemIndex);
//                printOrder();
//                System.out.printf("Going to position %x to get %s", getBestItemLocationIndex(), getBestItem());
//                System.out.println();
            }
            lastBestItem = getBestItem();

        }
    }
}
