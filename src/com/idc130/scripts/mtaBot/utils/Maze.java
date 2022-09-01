package com.idc130.scripts.mtaBot.utils;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Maze {
    private final Cell[][] cells = new Cell[10][10];
    private final Label[][] solution = new Label[10][10];
    private final int[] startLocation = new int[2];
    private final int[] goalLocation = new int[2];
    public boolean failedToBuild = false;
    private int minX;
    private int minY;

    public Maze() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                cells[x][y] = new Cell();
            }
        }

        var mazePieces = GameObjects.newQuery().ids(10755).defaultColors(new Color(94, 94, 94)).results().asList();

        if (mazePieces.size() == 0) {
            failedToBuild = true;
            return;
        }

        try {

            var minXOptional = mazePieces.stream().map(piece -> Objects.requireNonNull(piece.getPosition()).getX()).min(Comparator.comparingInt(a -> a));
            var minYOptional = mazePieces.stream().map(piece -> Objects.requireNonNull(piece.getPosition()).getY()).min(Comparator.comparingInt(a -> a));
            if (minYOptional.isPresent()) {
                minX = minXOptional.get() + 1;
                minY = minYOptional.get() + 1;
            } else {
                failedToBuild = true;
                return;
            }

            for (var piece : mazePieces) {
                addPieceToMaze(Objects.requireNonNull(piece.getPosition()), Objects.requireNonNull(piece.getDirection()).name(), Objects.requireNonNull(piece.getModel()).toString().equals("Model(origin: cache, components: 10674)"));
            }

            var goalPosition = Objects.requireNonNull(GameObjects.newQuery().ids(23672).results().nearest()).getPosition();
            var mazeGuardian = Objects.requireNonNull(Npcs.newQuery().names("Maze Guardian").results().nearest()).getPosition();

            startLocation[0] = Objects.requireNonNull(mazeGuardian).getX() - minX;
            startLocation[1] = mazeGuardian.getY() - minY;

            goalLocation[0] = Objects.requireNonNull(goalPosition).getX() - minX;
            goalLocation[1] = goalPosition.getY() - minY;

            if (outOfBounds(startLocation) || outOfBounds(goalLocation)) {
                failedToBuild = true;
                return;
            }

            solve();
        } catch (NullPointerException exception) {
            failedToBuild = true;
        }
    }

    private boolean outOfBounds(int[] coordinate) {
        return coordinate[0] < 0 || coordinate[0] > 10 || coordinate[1] < 0 || coordinate[1] > 10;
    }

    public Area getNextMove() {
        switch (getNextMoveString()) {
            case "north":
                return new Area.Rectangular(new Coordinate(minX, minY + 10, 0), new Coordinate(minX + 9, minY + 10, 0));
            case "south":
                return new Area.Rectangular(new Coordinate(minX, minY - 1, 0), new Coordinate(minX + 9, minY - 1, 0));
            case "east":
                return new Area.Rectangular(new Coordinate(minX + 10, minY, 0), new Coordinate(minX + 10, minY + 9, 0));
            case "west":
                return new Area.Rectangular(new Coordinate(minX - 1, minY, 0), new Coordinate(minX - 1, minY + 9, 0));
        }

        return null;
    }

    public String getNextMoveString() {
        return solution[goalLocation[0]][goalLocation[1]].firstMove;
    }

    private void addPieceToMaze(Coordinate location, String position, boolean isCornerPiece) {

        var normalisedX = location.getX() - minX;
        var normalisedY = location.getY() - minY;

        if (normalisedY < 0 || normalisedY >= 10 || normalisedX < 0 || normalisedX >= 10) return;

        boolean southBlocked = false;
        boolean northBlocked = false;
        boolean westBlocked = false;
        boolean eastBlocked = false;

        switch (position) {
            case "NORTH_WEST":
                southBlocked = true;
                if (isCornerPiece) {
                    westBlocked = true;
                }
                break;
            case "NORTH_EAST":
                westBlocked = true;
                if (isCornerPiece) {
                    northBlocked = true;
                }
                break;
            case "SOUTH_EAST":
                northBlocked = true;
                if (isCornerPiece) {
                    eastBlocked = true;
                }
                break;
            case "SOUTH_WEST":
                eastBlocked = true;
                if (isCornerPiece) {
                    southBlocked = true;
                }
                break;
        }

        if (southBlocked) {
            cells[normalisedX][normalisedY].southBlocked = true;
            if (normalisedY - 1 > 0)
                cells[normalisedX][normalisedY - 1].northBlocked = true;
        }
        if (westBlocked) {
            cells[normalisedX][normalisedY].westBlocked = true;
            if (normalisedX - 1 > 0)
                cells[normalisedX - 1][normalisedY].eastBlocked = true;
        }
        if (northBlocked) {
            cells[normalisedX][normalisedY].northBlocked = true;
            if (normalisedY + 1 < 10)
                cells[normalisedX][normalisedY + 1].southBlocked = true;
        }
        if (eastBlocked) {
            cells[normalisedX][normalisedY].eastBlocked = true;
            if (normalisedX + 1 < 10)
                cells[normalisedX + 1][normalisedY].westBlocked = true;
        }
    }

    private int[] getNextLocation(int[] start, Direction direction) {
        int newLength = start.length;

        int[] nextLocation = Arrays.copyOf(start, newLength);

        while (nextLocation[0] + direction.x >= 0 && nextLocation[0] + direction.x < 10 && nextLocation[1] + direction.y >= 0 && nextLocation[1] + direction.y < 10 && !cells[nextLocation[0]][nextLocation[1]].isBlocked(direction)) {
            nextLocation[0] += direction.x;
            nextLocation[1] += direction.y;
        }

        return nextLocation;
    }

    private boolean locationsEqual(int[] location1, int[] location2) {
        return location1[0] == location2[0] && location1[1] == location2[1];
    }

    private void solve() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                solution[x][y] = new Label();
            }
        }

        solution[startLocation[0]][startLocation[1]].distance = 0;

        var directions = new Direction[]{new Direction(0, 1, "north"), new Direction(0, -1, "south"), new Direction(1, 0, "east"), new Direction(-1, 0, "west")};

        for (int step = 0; step < 9; step++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    if (solution[x][y].distance == step) {
                        var location = new int[]{x, y};

                        for (var direction : directions) {
                            var nextNorthLocation = getNextLocation(location, direction);
                            if (!locationsEqual(location, nextNorthLocation)) {
                                if (solution[x][y].firstMove.equals("")) {
                                    solution[nextNorthLocation[0]][nextNorthLocation[1]].setIfUnassigned(step + 1, direction.sameDirection);
                                } else {
                                    solution[nextNorthLocation[0]][nextNorthLocation[1]].setIfUnassigned(step + 1, solution[x][y].firstMove);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}

class Direction {
    public final int x;
    public final int y;
    public final String sameDirection;

    public Direction(int x, int y, String sameDirection) {
        this.x = x;
        this.y = y;
        this.sameDirection = sameDirection;
    }
}

class Label {
    public int distance = -1;
    public String firstMove = "";

    public void setIfUnassigned(int distance, String firstMove) {
        if (this.distance == -1) {
            this.distance = distance;
            this.firstMove = firstMove;
        }
    }
}

class Cell {
    public boolean northBlocked = false;
    public boolean eastBlocked = false;
    public boolean southBlocked = false;
    public boolean westBlocked = false;

    public boolean isBlocked(Direction direction) {
        return eastBlocked && direction.x == 1 || westBlocked && direction.x == -1 || northBlocked && direction.y == 1 || southBlocked && direction.y == -1;
    }
}

