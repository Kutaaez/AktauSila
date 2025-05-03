package org.example.game;

public abstract class GameObject {
    protected int id;

    public GameObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract void render(); // например, вывод состояния или отображение на GUI
}
