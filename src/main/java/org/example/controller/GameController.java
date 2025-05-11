package org.example.controller;

import org.example.domain.player.BotPlayer;
import org.example.domain.player.IPlayer;
import org.example.domain.player.PlayerFactory;
import org.example.domain.command.ICommand;
import org.example.domain.command.MoveCommand;
import org.example.domain.command.ResetCommand;
import org.example.domain.facade.ToguzBoard;
import org.example.domain.observer.IStateObserver;
import org.example.view.screens.MainView;

public class GameController implements IGameController, IStateObserver {
    private final ToguzBoard model;
    private MainView view;
    private final IPlayer[] players;
    private final BotMoveScheduler botMoveScheduler;
    private boolean isPaused;

    public GameController(boolean twoPlayers) {
        this.model = new ToguzBoard();
        this.players = PlayerFactory.createPlayers(twoPlayers);
        this.botMoveScheduler = new BotMoveScheduler();
        this.isPaused = false;
        this.model.addObserver(this);
    }

    public void setView(MainView view) {
        this.view = view;
        view.update();
        if (!isPaused) {
            botMoveScheduler.scheduleBotMove(this, model, players);
        }
    }

    public void onHoleClicked(int holeIndex, boolean playerSide) {
        if (isPaused || model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        if (!isValidPlayerMove(currentPlayer, playerSide) || players[currentPlayer] instanceof BotPlayer) {
            return;
        }
        try {
            ICommand command = new MoveCommand(model, holeIndex, currentPlayer);
            if (command.execute()) {
                view.update();
                if (!isPaused) {
                    botMoveScheduler.scheduleBotMove(this, model, players);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to execute move: " + e.getMessage());
        }
    }

    public void onNewGame() {
        if (isPaused) {
            return; // Нельзя начать новую игру во время паузы
        }
        try {
            ICommand command = new ResetCommand(model);
            command.execute();
            view.update();
            botMoveScheduler.scheduleBotMove(this, model, players);
        } catch (Exception e) {
            System.err.println("Failed to reset game: " + e.getMessage());
        }
    }

    public void pauseGame() {
        if (!model.isGameFinished()) {
            isPaused = true;
            view.update(); // Обновить UI, чтобы заблокировать лунки
        }
    }

    public void resumeGame() {
        if (isPaused) {
            isPaused = false;
            view.update();
            botMoveScheduler.scheduleBotMove(this, model, players);
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    public int getCurrentPlayer() {
        return model.getCurrentColor();
    }

    public int getGameResult() {
        return model.getGameResult();
    }

    public int getHoleCount(int holeIndex) {
        return model.getHoleCount(holeIndex);
    }

    public int getOpponentHoleCount(int holeIndex) {
        return model.getOpponentHoleCount(holeIndex);
    }

    public int getKazan(int playerColor) {
        return model.getKazan(playerColor);
    }

    public int getTuzdyk(int playerColor) {
        return model.getTuzdyk(playerColor);
    }

    public boolean isFinished() {
        return model.isGameFinished();
    }

    public IPlayer[] getPlayers() {
        return players;
    }

    @Override
    public void onStateChanged() {
        if (view != null) {
            view.update();
        }
    }

    private boolean isValidPlayerMove(int currentPlayer, boolean playerSide) {
        return (currentPlayer == 0 && playerSide) || (currentPlayer == 1 && !playerSide);
    }
}