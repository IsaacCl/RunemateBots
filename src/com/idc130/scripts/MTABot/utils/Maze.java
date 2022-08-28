package com.idc130.scripts.MTABot.utils;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;

import java.util.Arrays;
import java.util.Comparator;

public class Maze {
    Cell[][] cells;
    int[] goalLocation;
    private final int minX;
    private final int minY;

    public Maze()
    {
        var mazePieces = GameObjects.newQuery().ids(10755).results().asList();

         minX = mazePieces.stream().map(piece -> piece.getPosition().getX()).min(Comparator.comparingInt(a -> a)).get() +1;
         minY = mazePieces.stream().map(piece -> piece.getPosition().getY()).min(Comparator.comparingInt(a -> a)).get() +1;

        for(var piece : mazePieces)
        {
            addPieceToMaze(piece.getPosition(), piece.getDirection().name());
        }

        solve();
    }

    public Area getNextMove()
    {
        return null;
    }

    private void addPieceToMaze(Coordinate location, String position)
    {
        var normalisedX = location.getX() - minX;
        var normalisedY = location.getY() - minY;

        if(normalisedY <0 || normalisedY >= 10 || normalisedX <0 || normalisedX >= 10) return;

        switch(position)
        {
            case "NORTH_WEST":
                cells[normalisedX][normalisedY].southBlocked = true;
                if(normalisedY-1 > 0)
                    cells[normalisedX][normalisedY-1].northBlocked = true;
                break;
            case "NORTH_EAST":
                cells[normalisedX][normalisedY].westBlocked = true;
                if(normalisedX-1 > 0)
                    cells[normalisedX-1][normalisedY].eastBlocked = true;
                break;
            case "SOUTH_EAST":
                cells[normalisedX][normalisedY].northBlocked = true;
                if(normalisedY+1 < 10)
                    cells[normalisedX][normalisedY+1].southBlocked = true;
                break;
            case "SOUTH_WEST":
                cells[normalisedX][normalisedY].eastBlocked = true;
                if(normalisedX+1 < 10)
                    cells[normalisedX+1][normalisedY].westBlocked = true;
                break;
        }
    }

    private int[] getNextLocation(int[] start, Direction direction)
    {
        int newLength = start.length;

        int[] nextLocation = Arrays.copyOf(start, newLength);

        while(nextLocation[0] >= 0 && nextLocation[0] < 10 && nextLocation[1] >= 0 && nextLocation[1] < 10 && cells[nextLocation[0]][nextLocation[1]].isBlocked(direction))
        {
            nextLocation[0] += direction.x;
            nextLocation[1] += direction.y;
        }

        return nextLocation;
    }

    private boolean locationsEqual(int[] location1, int[] location2)
    {
        return location1[0] == location2[0] && location1[1] == location2[1];
    }

    private void solve()
    {
        Label[][] solution = new Label[10][10];
        for(int x=0; x<10; x++)
        {
            for(int y=0; y<10; y++)
            {
                solution[x][y] = new Label();
            }
        }

        solution[goalLocation[0]][goalLocation[1]].distance = 0;

        var directions = new Direction[]{new Direction(0, 1, "south"),new Direction(0, -1, "north"), new Direction(1, 0, "west"), new Direction(-1, 0, "east")};

        for(int step = 0; step < 10; step++)
        {
            for(int x=0; x<10; x++)
            {
                for(int y=0; y<10; y++)
                {
                    if(solution[x][y].distance == step)
                    {
                        var location = new int[]{x, y};

                        for(var direction : directions) {
                            var nextNorthLocation = getNextLocation(location, direction);
                            if (!locationsEqual(location, nextNorthLocation)) {
                                solution[nextNorthLocation[0]][nextNorthLocation[1]].setIfUnassigned(step + 1, direction.oppositeDirection);
                            }
                        }
                    }
                }
            }
        }
    }
}

class Direction {
    public int x;
    public int y;
    public String oppositeDirection;

    public Direction(int x, int y, String oppositeDirection)
    {
        this.x = x;
        this.y = y;
        this.oppositeDirection = oppositeDirection;
    }
}

class Label {
    public int distance = -1;
    public String lastMove = "";

    public void setIfUnassigned(int distance, String lastMove)
    {
        if(this.distance == -1)
        {
            this.distance = distance;
            this.lastMove = lastMove;
        }
    }
}

class Cell {
    public boolean northBlocked;
    public boolean eastBlocked;
    public boolean southBlocked;
    public boolean westBlocked;

    public Cell(boolean northBlocked, boolean eastBlocked, boolean southBlocked, boolean westBlocked)
    {
        this.northBlocked = northBlocked;
        this.eastBlocked = eastBlocked;
        this.southBlocked = southBlocked;
        this.westBlocked = westBlocked;
    }

    public boolean isBlocked(Direction direction)
    {
        return eastBlocked && direction.x == 1 || westBlocked && direction.x == -1 || northBlocked && direction.y == 1 || southBlocked && direction.y == -1;
    }
}

