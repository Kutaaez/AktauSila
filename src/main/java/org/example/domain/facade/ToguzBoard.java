package org.example.domain.facade;

import org.example.domain.board.BoardState;
import org.example.domain.board.CurrentPlayer;
import org.example.domain.board.Holes;
import org.example.domain.strategy.MoveStrategyFactory;
import org.example.domain.observer.IStateObserver;
import org.example.domain.game.IGameState;
import org.example.domain.game.GameStateImpl;
import org.example.domain.strategy.IMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class ToguzBoard {
    private final BoardState boardState;
    private final IGameState gameState;
    private final IMoveStrategy moveStrategy;
    private final List<IStateObserver> observers;

    public ToguzBoard() {
        this.boardState = new BoardState();
        this.gameState = new GameStateImpl(boardState);
        this.moveStrategy = MoveStrategyFactory.createStandardMoveStrategy();
        this.observers = new ArrayList<>();
    }

    public boolean executeMove(int hole, int playerColor) {
        if (gameState.isFinished() || playerColor != boardState.getCurrentPlayer().getCurrentPlayer()) {
            return false;
        }
        boolean success = moveStrategy.executeMove(boardState, hole, playerColor);
        if (success) {
            gameState.checkGameState();
            notifyObservers();
        }
        return success;
    }

    public List<Integer> getAvailableMoves(int playerColor) {
        List<Integer> moves = new ArrayList<>();
        int start = (playerColor == CurrentPlayer.WHITE_PLAYER) ? 0 : Holes.HOLES_PER_PLAYER;
        int end = start + Holes.HOLES_PER_PLAYER - 1;
        for (int i = start; i <= end; i++) {
            if (boardState.getHoles().getSeedCount(i) > 0 && !boardState.getHoles().isTuzdyk(i)) {
                moves.add(i - start + 1);
            }
        }
        return moves;
    }

    public int getGameResult() {
        return gameState.getResult();
    }

    public String getScore() {
        return boardState.getKazans().getKazan(CurrentPlayer.WHITE_PLAYER) + " - " +
                boardState.getKazans().getKazan(CurrentPlayer.BLACK_PLAYER);
    }

    public int getCurrentColor() {
        return boardState.getCurrentPlayer().getCurrentPlayer();
    }

    public boolean isGameFinished() {
        return gameState.isFinished();
    }

    public int getTuzdyk(int playerColor) {
        return boardState.getTuzdyks().getTuzdyk(playerColor);
    }

    public int getKazan(int playerColor) {
        return boardState.getKazans().getKazan(playerColor);
    }

    public int getHoleCount(int hole) {
        if (hole < 1 || hole > Holes.HOLES_PER_PLAYER) {
            throw new IllegalArgumentException("Hole index must be 1.." + Holes.HOLES_PER_PLAYER);
        }
        return boardState.getHoles().getSeedCount(hole - 1);
    }

    public int getOpponentHoleCount(int hole) {
        if (hole < 1 || hole > Holes.HOLES_PER_PLAYER) {
            throw new IllegalArgumentException("Hole index must be 1.." + Holes.HOLES_PER_PLAYER);
        }
        return boardState.getHoles().getSeedCount(hole - 1 + Holes.HOLES_PER_PLAYER);
    }

    public void reset() {
        boardState.reset();
        gameState.reset();
        notifyObservers();
    }

    public void addObserver(IStateObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (IStateObserver observer : observers) {
            observer.onStateChanged();
        }
    }
}