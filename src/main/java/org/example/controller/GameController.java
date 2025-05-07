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

public class GameController implements IGameController,     IStateObserver {
    private final ToguzBoard model;
    private MainView view;
    private final IPlayer[] players;
    private final BotMoveScheduler botMoveScheduler;

    public GameController(boolean twoPlayers) {
        this.model = new ToguzBoard();
        this.players = PlayerFactory.createPlayers(twoPlayers);
        this.botMoveScheduler = new BotMoveScheduler();
        this.model.addObserver(this);
    }

    public void setView(MainView view) {
        this.view = view;
        view.update();
        botMoveScheduler.scheduleBotMove(this, model, players);
    }

    public void onHoleClicked(int holeIndex, boolean playerSide) {
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        boolean isPlayerSide = (currentPlayer == 0 && playerSide) || (currentPlayer == 1 && !playerSide);
        if (!isPlayerSide || players[currentPlayer] instanceof BotPlayer) {
            return;
        }
        ICommand command = new MoveCommand(model, holeIndex, currentPlayer);
        if (command.execute()) {
            view.update();
            botMoveScheduler.scheduleBotMove(this, model, players);
        }
    }

    public void onNewGame() {
        ICommand command = new ResetCommand(model);
        command.execute();
        view.update();
        botMoveScheduler.scheduleBotMove(this, model, players);
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
}