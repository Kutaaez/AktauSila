package org.example.domain.game;

public interface IGameState {
    boolean isFinished();
    int getResult();
    void checkGameState();
    void reset();
}