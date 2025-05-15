package org.example.domain.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameResultDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/togyzqumalaq";
    private static final String USER = "postgres";
    private static final String PASS = "1909";
    private static volatile long lastRequestTime = 0;
    private static final long RATE_LIMIT_MS = 10_000; // 10 seconds in milliseconds

    public static boolean saveGameResult(String winner, String gameMode) {
        // Check if enough time has passed since the last request
        synchronized (GameResultDAO.class) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastRequestTime < RATE_LIMIT_MS) {
                return false; // Rate limit exceeded
            }
            lastRequestTime = currentTime;
        }

        String sql = "INSERT INTO game_results (winner, game_mode) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, winner);
            stmt.setString(2, gameMode);
            stmt.executeUpdate();
            return true; // Successfully saved

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Failed to save
        }
    }

    public static List<GameResult> fetchAll() {
        List<GameResult> results = new ArrayList<>();
        String sql = "SELECT * FROM game_results ORDER BY timestamp DESC";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                GameResult r = new GameResult(
                        rs.getString("winner"),
                        rs.getString("game_mode"),
                        rs.getTimestamp("timestamp")
                );
                results.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}