package org.example.domain.game;

import org.example.domain.board.BoardState;
import org.example.domain.board.CurrentPlayer;
import org.example.domain.board.Holes;

public class GameResultChecker {
    public static final int TOTAL_SEEDS = 162;
    public static final int WIN_THRESHOLD = 82;
    public static final int DRAW_THRESHOLD = 81;

    public void checkGameState(BoardState boardState, GameStateImpl gameState) {
        int whiteSum = calculatePlayerSeeds(boardState, CurrentPlayer.WHITE_PLAYER);
        int blackSum = TOTAL_SEEDS - whiteSum -
                boardState.getKazans().getKazan(CurrentPlayer.WHITE_PLAYER) -
                boardState.getKazans().getKazan(CurrentPlayer.BLACK_PLAYER);

        if (boardState.getCurrentPlayer().getCurrentPlayer() == CurrentPlayer.WHITE_PLAYER && whiteSum == 0) {
            boardState.getKazans().addToKazan(CurrentPlayer.BLACK_PLAYER, blackSum);
        } else if (boardState.getCurrentPlayer().getCurrentPlayer() == CurrentPlayer.BLACK_PLAYER && blackSum == 0) {
            boardState.getKazans().addToKazan(CurrentPlayer.WHITE_PLAYER, whiteSum);
        }

        int whiteKazan = boardState.getKazans().getKazan(CurrentPlayer.WHITE_PLAYER);
        int blackKazan = boardState.getKazans().getKazan(CurrentPlayer.BLACK_PLAYER);
        if (whiteKazan >= WIN_THRESHOLD) {
            gameState.setFinished(true);
            gameState.setGameResult(1); // White wins
        } else if (blackKazan >= WIN_THRESHOLD) {
            gameState.setFinished(true);
            gameState.setGameResult(-1); // Black wins
        } else if (whiteKazan == DRAW_THRESHOLD && blackKazan == DRAW_THRESHOLD) {
            gameState.setFinished(true);
            gameState.setGameResult(0); // Draw
        }
    }

    private int calculatePlayerSeeds(BoardState boardState, int playerColor) {
        int start = (playerColor == CurrentPlayer.WHITE_PLAYER) ? 0 : Holes.HOLES_PER_PLAYER;
        int end = start + Holes.HOLES_PER_PLAYER;
        int sum = 0;
        for (int i = start; i < end; i++) {
            if (!boardState.getHoles().isTuzdyk(i)) {
                sum += boardState.getHoles().getSeedCount(i);
            }
        }
        return sum;
    }
}