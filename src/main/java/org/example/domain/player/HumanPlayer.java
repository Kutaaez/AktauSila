package org.example.domain.player;

public class HumanPlayer implements IPlayer {
    private final int color;

    public HumanPlayer(int color) {
        this.color = color;
    }



    @Override
    public int getColor() {
        return color;
    }
}