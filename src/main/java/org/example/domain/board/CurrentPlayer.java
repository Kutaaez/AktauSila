package org.example.domain.board;
public class CurrentPlayer {
    public static final int WHITE_PLAYER = 0;
    public static final int BLACK_PLAYER = 1;

    private int currentPlayer;

    public CurrentPlayer() {
        reset();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = 1 - currentPlayer;
    }

    public void reset() {
        currentPlayer = WHITE_PLAYER;
    }
}