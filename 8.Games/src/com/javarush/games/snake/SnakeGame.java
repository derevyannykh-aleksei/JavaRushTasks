package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    private static final int GOAL = 28;

    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;

    private void createGame() {
        turnDelay = 300;
        setTurnTimer(turnDelay);

        
        Snake newSnake = new Snake(WIDTH / 2, HEIGHT / 2);
        this.snake = newSnake;
        createNewApple();
        isGameStopped = false;

        drawScene();
    }
    
    private void drawScene() {
        for (int x = 0; x < WIDTH; x++)
            for (int y = 0; y < HEIGHT; y++)
                setCellValueEx(x, y, Color.DARKSEAGREEN, "");
        snake.draw(this);
        apple.draw(this);
    }
    
    @Override
    public void initialize() {
        setScreenSize(15, 15);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive) {
            createNewApple();
        }
        if (!snake.isAlive) {
            gameOver();
        }
        if (snake.getLength() > GOAL) {
            win();
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key.equals(Key.LEFT))
            snake.setDirection(Direction.LEFT);
        else if (key.equals(Key.RIGHT))
            snake.setDirection(Direction.RIGHT);
        else if (key.equals(Key.UP))
            snake.setDirection(Direction.UP);
        else if (key.equals(Key.DOWN))
            snake.setDirection(Direction.DOWN);
    }

    private void createNewApple() {
        apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE,"GAME OVER", Color.RED,75);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.GREEN, 75);
    }

}