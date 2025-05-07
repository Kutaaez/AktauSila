package org.example.controller;

import javafx.application.Platform;
import org.example.domain.player.BotPlayer;
import org.example.domain.player.IPlayer;
import org.example.domain.player.PlayerFactory;
import org.example.domain.command.ICommand;
import org.example.domain.command.MoveCommand;
import org.example.domain.command.ResetCommand;
import org.example.domain.facade.ToguzBoard;
import org.example.domain.observer.IStateObserver;
import org.example.view.screens.MainView;
import org.example.view.components.ConfirmModal;

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
        Platform.runLater(() -> {
            view.update();
            if (!isPaused && !model.isGameFinished()) {
                botMoveScheduler.scheduleBotMove(this, model, players);
            }
        });
    }

    @Override
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
                Platform.runLater(() -> {
                    view.update();
                    if (!isPaused && !model.isGameFinished()) {
                        botMoveScheduler.scheduleBotMove(this, model, players);
                    }
                });
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                ConfirmModal modal = new ConfirmModal("Error", "Failed to make move: " + e.getMessage());
                modal.show();
            });
        }
    }

    @Override
    public void onNewGame() {
        if (isPaused) {
            return; // Нельзя начать новую игру во время паузы
        }
        try {
            ICommand command = new ResetCommand(model);
            command.execute();
            Platform.runLater(() -> {
                view.update();
                if (!isPaused && !model.isGameFinished()) {
                    botMoveScheduler.scheduleBotMove(this, model, players);
                }
            });
        } catch (Exception e) {
            Platform.runLater(() -> {
                ConfirmModal modal = new ConfirmModal("Error", "Failed to reset game: " + e.getMessage());
                modal.show();
            });
        }
    }

    public void pauseGame() {
        if (!model.isGameFinished() && !isPaused) {
            isPaused = true;
            Platform.runLater(() -> view.update());
        }
    }

    public void resumeGame() {
        if (isPaused) {
            isPaused = false;
            Platform.runLater(() -> {
                view.update();
                if (!model.isGameFinished()) {
                    botMoveScheduler.scheduleBotMove(this, model, players);
                }
            });
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public int getCurrentPlayer() {
        return model.getCurrentColor();
    }

    @Override
    public int getGameResult() {
        return model.getGameResult();
    }

    @Override
    public int getHoleCount(int holeIndex) {
        return model.getHoleCount(holeIndex);
    }

    @Override
    public int getOpponentHoleCount(int holeIndex) {
        return model.getOpponentHoleCount(holeIndex);
    }

    @Override
    public int getKazan(int playerColor) {
        return model.getKazan(playerColor);
    }

    @Override
    public int getTuzdyk(int playerColor) {
        return model.getTuzdyk(playerColor);
    }

    @Override
    public boolean isFinished() {
        return model.isGameFinished();
    }

    public IPlayer[] getPlayers() {
        return players;
    }

    @Override
    public void onStateChanged() {
        if (view != null) {
            Platform.runLater(() -> view.update());
        }
    }

    private boolean isValidPlayerMove(int currentPlayer, boolean playerSide) {
        return (currentPlayer == 0 && playerSide) || (currentPlayer == 1 && !playerSide);
    }
}