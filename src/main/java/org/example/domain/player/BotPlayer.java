package org.example.domain.player;

import org.example.domain.facade.ToguzBoard;

import java.util.List;
import java.util.Random;

/**
 * A bot player that makes random moves in the Toguz Kumalak game.
 */
public class BotPlayer implements IPlayer {
    private final int color;
    private final Random random;

    /**
     * Constructs a BotPlayer with the specified color.
     *
     * @param color The color of the bot (0 for white, 1 for black).
     */
    public BotPlayer(int color) {
        this.color = color;
        this.random = new Random();
    }

    /**
     * Makes a random move from the available moves on the board.
     *
     * @param board The game board.
     * @return The index of the chosen hole (1-9), or -1 if no moves are available.
     * @throws IllegalStateException if the game is finished or no valid moves exist.
     */
    @Override
    public int makeMove(ToguzBoard board) {
        if (board.isGameFinished()) {
            throw new IllegalStateException("Cannot make a move: game is finished.");
        }
        List<Integer> moves = board.getAvailableMoves(color);
        if (moves.isEmpty()) {
            return -1; // No valid moves available
        }
        return moves.get(random.nextInt(moves.size()));
    }

    /**
     * Returns the color of the bot player.
     *
     * @return The color (0 for white, 1 for black).
     */
    @Override
    public int getColor() {
        return color;
    }
}