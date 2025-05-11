package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.controller.GameController;
import org.example.domain.player.BotPlayer;

public class ScoreBoard {
    private final VBox container;
    private final Label playerScore;
    private final Label opponentScore;

    public ScoreBoard(GameController controller) {
        container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(10));
        container.getStyleClass().add("score-board");

        playerScore = new Label("0");
        playerScore.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1a1a1a;");
        playerScore.setPadding(new Insets(5));

        opponentScore = new Label("0");
        opponentScore.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1a1a1a;");
        opponentScore.setPadding(new Insets(5));

        boolean isBotGame = controller.getPlayers()[1] instanceof BotPlayer;
        Label label1 = new Label(isBotGame ? "Player Kazan:" : "Player 1 Kazan:");
        label1.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
        Label label2 = new Label(isBotGame ? "Bot Kazan:" : "Player 2 Kazan:");
        label2.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");

        HBox player1Box = new HBox(5, label1, playerScore);
        player1Box.setAlignment(Pos.CENTER);
        HBox player2Box = new HBox(5, label2, opponentScore);
        player2Box.setAlignment(Pos.CENTER);

        container.getChildren().addAll(player1Box, player2Box);
    }

    public void setScores(int player1, int player2) {
        playerScore.setText(String.valueOf(player1));
        opponentScore.setText(String.valueOf(player2));
    }

    public VBox getNode() {
        return container;
    }
}