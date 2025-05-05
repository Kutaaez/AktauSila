package org.example.domain.command;

import org.example.domain.facade.ToguzBoard;

public class ResetCommand implements ICommand {
    private final ToguzBoard board;

    public ResetCommand(ToguzBoard board) {
        this.board = board;
    }

    @Override
    public boolean execute() {
        board.reset();
        return true;
    }
}