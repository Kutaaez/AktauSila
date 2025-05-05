package org.example.domain.player;

import java.util.List;
import java.util.Random;

public class BotPlayer implements IPlayer {
    private final int color;
    private final Random random;

    public BotPlayer(int color) {
        this.color = color;
        this.random = new Random();
    }
    @Override
    public int getColor() {
        return color;
    }
}