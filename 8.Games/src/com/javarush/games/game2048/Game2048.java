package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game{
    
    private static final int SIDE = 4;
    
    private int[][] gameField = new int[SIDE][SIDE];
    
    private void createGame() {
        createNewNumber();
        createNewNumber();
    }
    
    private void drawScene() {
        for(int y = 0; y < SIDE; y++)
            for (int x = 0; x < SIDE; x++)
                setCellColoredNumber(x, y, gameField[y][x]);
    }
    
    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createNewNumber() {

        int x = getRandomNumber(SIDE);
        int y = getRandomNumber(SIDE);
        int number = getRandomNumber(10);

        if (gameField[x][y] == 0) {
            if (number < 9) {
                gameField[x][y] = 2;
            }
            else
                gameField[x][y] = 4;
        } else
            createNewNumber();
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.WHITE;
            case 2:
                return Color.PLUM;
            case 4:
                return Color.SLATEBLUE;
            case 8:
                return Color.DODGERBLUE;
            case 16:
                return Color.DARKTURQUOISE;
            case 32:
                return Color.MEDIUMSEAGREEN;
            case 64:
                return Color.LIMEGREEN;
            case 128:
                return Color.DARKORANGE;
            case 256:
                return Color.SALMON;
            case 512:
                return Color.ORANGERED;
            case 1024:
                return Color.DEEPPINK;
            case 2048:
                return Color.MEDIUMVIOLETRED;
            default:
                return Color.NONE;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        Color newColor = getColorByValue(value);
        String strValue = value > 0 ? "" + value : "";
        setCellValueEx(x, y,newColor, strValue);
    }
}