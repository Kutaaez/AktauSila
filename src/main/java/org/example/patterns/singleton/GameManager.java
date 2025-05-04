package org.example.patterns.singleton;

public class GameManager {
    private static GameManager instance;

    private GameManager() {
        // приватный конструктор
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame() {
        System.out.println("Игра началась!");
    }

    public void endGame() {
        System.out.println("Игра завершена.");
    }
}
