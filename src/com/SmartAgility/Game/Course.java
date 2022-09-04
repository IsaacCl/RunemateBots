package com.SmartAgility.Game;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.details.Locatable;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.AbstractBot;

import java.util.ArrayList;
import java.util.Arrays;


public class Course {

    private ArrayList<Obstacle> obstacleList = new ArrayList<>();
    int minLevel;
    String name = "";

    private AbstractBot bot;

    private CustomBank bank;

    Course(String name, int minLevel, Obstacle... obstacles)
    {
        this.name = name;
        this.minLevel = minLevel;
        if(obstacles != null) {
            obstacleList.addAll(Arrays.asList(obstacles));
        }

        this.bot = Environment.getBot();
    }

    Course(String name, int minLevel, CustomBank bank, Obstacle... obstacles)
    {
        this.name = name;
        this.minLevel = minLevel;
        if(obstacles != null) {
            obstacleList.addAll(Arrays.asList(obstacles));
        }

        this.bot = Environment.getBot();
        this.bank = bank;
    }

    public void doNextObstacle()
    {
        for(int i=0; i<obstacleList.size(); i++)
        {
            if(obstacleList.get(i).readyToStart())
            {
                if(i < obstacleList.size() - 2)
                    obstacleList.get(i).doObstacle(obstacleList.get(i+2));
                else if(i == obstacleList.size() - 2)
                {
                    obstacleList.get(i).doObstacle(obstacleList.get(i+1));
                } else
                {
                    obstacleList.get(i).doObstacle(obstacleList.get(0));
                }
                return;
            }
        }

        bot.getLogger().debug("Returning to appropriate area");

        returnToNearestObstacle();
    }

    private Obstacle getNearestObstacleTo(Locatable object)
    {
        Obstacle bestObstacle = obstacleList.get(0);
        double shortestDistance = bestObstacle.getDistanceFromObject(object);
        for(Obstacle obstacle : obstacleList) {
            if(obstacle.getStartingArea().contains(object))
            {
                return obstacle;
            }
            double distance = obstacle.getDistanceFromObject(object);
            if(distance < shortestDistance)
            {
                shortestDistance = distance;
                bestObstacle = obstacle;
                bot.getLogger().debug("Nearest obstacle " + bestObstacle.getName() + " at distance " + shortestDistance);
            }
        }

        return bestObstacle;
    }

    private void returnToNearestObstacle()
    {
        Player localPlayer = Players.getLocal();
        Obstacle bestObstacle = getNearestObstacleTo(localPlayer);
        bestObstacle.walkToObstacle();
    }

    public boolean isPlayerInSameArea(Locatable object)
    {
        Player localPlayer = Players.getLocal();
        Obstacle playerObstacle = getNearestObstacleTo(localPlayer);
        bot.getLogger().debug("Player is at obstacle" + playerObstacle.getName());
        Obstacle objectObstacle = getNearestObstacleTo(object);
        bot.getLogger().debug("Mark is at obstacle" + objectObstacle.getName());
        return playerObstacle == objectObstacle;
    }

    public boolean doingObstacle()
    {
        for(Obstacle obstacle : obstacleList)
        {
            if(obstacle.completingAction())
            {
                bot.getLogger().debug("Already completing action: " + obstacle.getName());
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerInFirstArea() {
        return obstacleList.get(0).readyToStart();
    }

    public double getDistanceFromCourse(Locatable object) {
        Obstacle obstacle = getNearestObstacleTo(object);
        return obstacle.getDistanceFromObject(object);
    }

    public CustomBank getBank()
    {
        return bank;
    }

    public boolean hasBank()
    {
        return bank != null;
    }
}
