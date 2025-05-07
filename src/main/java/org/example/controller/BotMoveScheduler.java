package org.example.controller;

import org.example.domain.player.BotPlayer;
import org.example.domain.player.IPlayer;
import org.example.domain.command.ICommand;
import org.example.domain.command.MoveCommand;
import org.example.domain.facade.ToguzBoard;

public class BotMoveScheduler {
    public void scheduleBotMove(GameController controller, ToguzBoard model, IPlayer[] players) {
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        if (players[currentPlayer] instanceof BotPlayer) {
            int move = players[currentPlayer].makeMove(model);
            if (move != -1) {
                ICommand command = new MoveCommand(model, move, currentPlayer);
                command.execute();
            }
        }
    }
}