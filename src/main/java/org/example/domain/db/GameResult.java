package org.example.domain.db;
import java.sql.Timestamp;

public class GameResult {
    private final String winner;
    private final String gameMode;
    private final Timestamp timestamp;

    public GameResult(String winner, String gameMode, Timestamp timestamp) {
        this.winner = winner;
        this.gameMode = gameMode;
        this.timestamp = timestamp;
    }

    public String getWinner() { return winner; }
    public String getGameMode() { return gameMode; }
    public Timestamp getTimestamp() { return timestamp; }
}