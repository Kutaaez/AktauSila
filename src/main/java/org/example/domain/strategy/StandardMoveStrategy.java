package org.example.domain.strategy;

import org.example.domain.board.BoardState;
import org.example.domain.board.Holes;
import org.example.domain.board.Kazans;
import org.example.domain.rules.IRules;

public class StandardMoveStrategy implements IMoveStrategy {
    private final IRules rules;

    public StandardMoveStrategy(IRules rules) {
        this.rules = rules;
    }

    @Override
    public boolean executeMove(BoardState boardState, int hole, int playerColor) {
        int idx = hole + Holes.HOLES_PER_PLAYER * playerColor - 1;
        if (hole < 1 || hole > Holes.HOLES_PER_PLAYER ||
                boardState.getHoles().getSeedCount(idx) == 0 ||
                boardState.getHoles().isTuzdyk(idx)) {
            return false;
        }

        int seeds = boardState.getHoles().getSeedCount(idx);
        int sow = sowSeeds(boardState, idx, seeds);
        idx = (idx + sow) % Holes.TOTAL_HOLES;

        if (rules.canFormTuzdyk(idx, playerColor, boardState)) {
            rules.applyTuzdyk(idx, playerColor, boardState);
        } else if (rules.canCapture(idx, playerColor, boardState)) {
            rules.applyCapture(idx, playerColor, boardState);
        }

        boardState.getCurrentPlayer().switchPlayer();
        return true;
    }

    private int sowSeeds(BoardState boardState, int idx, int seeds) {
        int sow;
        if (seeds == 1) {
            boardState.getHoles().setSeedCount(idx, 0);
            sow = 1;
        } else {
            boardState.getHoles().setSeedCount(idx, 1);
            sow = seeds - 1;
        }

        for (int i = 0; i < sow; i++) {
            idx = (idx + 1) % Holes.TOTAL_HOLES;
            if (boardState.getHoles().isTuzdyk(idx)) {
                int kazanIndex = (idx >= Holes.HOLES_PER_PLAYER) ? Kazans.WHITE_KAZAN : Kazans.BLACK_KAZAN;
                boardState.getKazans().addToKazan(kazanIndex, 1);
            } else {
                boardState.getHoles().setSeedCount(idx, boardState.getHoles().getSeedCount(idx) + 1);
            }
        }
        return sow;
    }
}