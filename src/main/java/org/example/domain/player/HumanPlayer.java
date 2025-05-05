package org.example.domain.player;

import org.example.domain.facade.ToguzBoard;

public class HumanPlayer implements IPlayer {
    private final int color;

    public HumanPlayer(int color) {
        this.color = color;
    }

    @Override
    public int makeMove(ToguzBoard board) {
        return -1;
    }

    @Override
    public int getColor() {
        return color;
    }
}