package org.example.domain.command;

import org.example.domain.facade.ToguzBoard;

public class MoveCommand implements ICommand {
    private final ToguzBoard board;
    private final int hole;
    private final int playerColor;

    public MoveCommand(ToguzBoard board, int hole, int playerColor) {
        this.board = board;
        this.hole = hole;
        this.playerColor = playerColor;
    }

    @Override
    public boolean execute() {
        return board.executeMove(hole, playerColor);
    }
}