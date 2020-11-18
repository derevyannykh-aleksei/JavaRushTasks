package com.javarush.games.racer.road;

import com.javarush.games.racer.*;
import com.javarush.engine.cell.Game;

import java.util.*;

public class RoadManager {

    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;

    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();


    public void draw(Game game) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(game);
        }
    }

    public void move(int boost) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).move(boost + items.get(i).speed);
        }
        deletePassedItems();
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
    }

    public boolean checkCrush(PlayerCar playerCar) {
        for (RoadObject item: items) {
            if (item.isCollision(playerCar))
                return true;
        }
        return false;
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type == RoadObjectType.THORN) {
            return new Thorn(x, y);
        } else if(type != RoadObjectType.THORN) {
            return new Car(type, x, y);
        } else
            return null;
    }

    private void addRoadObject(RoadObjectType roadObjectType, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(roadObjectType);
        RoadObject roadObject = createRoadObject(roadObjectType, x, y);
        if (roadObject != null) {
            items.add(roadObject);
        }
    }

    private boolean isThornExists() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Thorn) {
                return true;
            }
        }
        return false;
    }

    private void generateThorn(Game game) {
        if (game.getRandomNumber(100) < 10 && !isThornExists() ) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void generateRegularCar(Game game) {
        int carTypeNumber = game.getRandomNumber(4);
        if (game.getRandomNumber(100) < 30) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private void deletePassedItems() {
        items.removeIf(item -> item.y >= RacerGame.HEIGHT);
    }
}
