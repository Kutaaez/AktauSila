package org.example.domain.game;

import org.example.domain.board.BoardState;

public class GameStateImpl implements IGameState {
    private boolean finished;
    private int gameResult;
    private final BoardState boardState;

    public GameStateImpl(BoardState boardState) {
        this.boardState = boardState;
        this.finished = false;
        this.gameResult = -2; // Ongoing
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public int getResult() {
        return gameResult;
    }


    @Override
    public void reset() {
        finished = false;
        gameResult = -2;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setGameResult(int gameResult) {
        this.gameResult = gameResult;
    }
}