package org.example.domain.rules;

import org.example.domain.board.BoardState;
import org.example.domain.board.CurrentPlayer;
import org.example.domain.board.Holes;
import org.example.domain.board.Kazans;

public class StandardRules implements IRules {
    public static final int TUZDYK_SEEDS = 3;

    @Override
    public boolean canFormTuzdyk(int index, int playerColor, BoardState boardState) {
        if (boardState.getHoles().getSeedCount(index) != TUZDYK_SEEDS) {
            return false;
        }
        if (playerColor == CurrentPlayer.WHITE_PLAYER) {
            return boardState.getTuzdyks().getTuzdyk(CurrentPlayer.WHITE_PLAYER) == 0 &&
                    index > Holes.HOLES_PER_PLAYER &&
                    index < Holes.TOTAL_HOLES - 1 &&
                    boardState.getTuzdyks().getTuzdyk(CurrentPlayer.BLACK_PLAYER) != index - Holes.HOLES_PER_PLAYER + 1;
        } else {
            return boardState.getTuzdyks().getTuzdyk(CurrentPlayer.BLACK_PLAYER) == 0 &&
                    index < Holes.HOLES_PER_PLAYER &&
                    boardState.getTuzdyks().getTuzdyk(CurrentPlayer.WHITE_PLAYER) != index + 1;
        }
    }

    @Override
    public void applyTuzdyk(int index, int playerColor, BoardState boardState) {
        int opponentKazan = (playerColor == CurrentPlayer.WHITE_PLAYER) ? Kazans.BLACK_KAZAN : Kazans.WHITE_KAZAN;
        boardState.getKazans().addToKazan(opponentKazan, TUZDYK_SEEDS);
        boardState.getHoles().setSeedCount(index, Holes.TUZDYK_MARKER);
        boardState.getTuzdyks().setTuzdyk(playerColor, (playerColor == CurrentPlayer.WHITE_PLAYER) ? index - Holes.HOLES_PER_PLAYER + 1 : index + 1);
    }

    @Override
    public boolean canCapture(int index, int playerColor, BoardState boardState) {
        return boardState.getHoles().getSeedCount(index) % 2 == 0 &&
                ((playerColor == CurrentPlayer.WHITE_PLAYER && index >= Holes.HOLES_PER_PLAYER) ||
                        (playerColor == CurrentPlayer.BLACK_PLAYER && index < Holes.HOLES_PER_PLAYER));
    }

    @Override
    public void applyCapture(int index, int playerColor, BoardState boardState) {
        int seeds = boardState.getHoles().getSeedCount(index);
        boardState.getKazans().addToKazan(playerColor, seeds);
        boardState.getHoles().setSeedCount(index, 0);
    }
}

//strategy pattern
//This uses the Strategy pattern to
//encapsulate different game-rule algorithms in interchangeable IRules implementations.