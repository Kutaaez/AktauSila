package org.example.game;

public interface GameEventListener {
    void onMove(int fromPit, int toPit, int ballsMoved);
    void onCapture(int pit, int ballsCaptured);
    void onGameOver(String winner);
}
