package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.controller.GameController;
import org.example.domain.player.BotPlayer;

public class PlayerPanel {
    private final VBox node;
    private final Label playerScore;
    private final Label opponentScore;
    private final Label currentPlayerLabel;
    private final Label playerLabel;
    private final Label opponentLabel;

    public PlayerPanel(GameController controller) {
        node = new VBox(10);
        node.setAlignment(Pos.CENTER);
        node.setPadding(new Insets(10));
        node.getStyleClass().add("score-board");

        // Determine player names based on game mode
        boolean isBotGame = controller.getPlayers()[1] instanceof BotPlayer;
        playerLabel = new Label(isBotGame ? "Player" : "Player 1");
        playerLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

        playerScore = new Label("0");
        playerScore.setFont(Font.font("System", FontWeight.NORMAL, 14));

        opponentLabel = new Label(isBotGame ? "Bot" : "Player 2");
        opponentLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

        opponentScore = new Label("0");
        opponentScore.setFont(Font.font("System", FontWeight.NORMAL, 14));

        currentPlayerLabel = new Label("Current: " + (isBotGame ? "Player" : "Player 1"));
        currentPlayerLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        node.getChildren().addAll(playerLabel, playerScore, opponentLabel, opponentScore, currentPlayerLabel);
    }

    public void setScores(int playerKazan, int opponentKazan) {
        playerScore.setText(String.valueOf(playerKazan));
        opponentScore.setText(String.valueOf(opponentKazan));
    }

    public void setCurrentPlayer(int currentPlayer) {
        boolean isBotGame = opponentLabel.getText().equals("Bot");
        if (isBotGame) {
            currentPlayerLabel.setText(currentPlayer == 0 ? "Current: Player" : "Current: Bot");
        } else {
            currentPlayerLabel.setText(currentPlayer == 0 ? "Current: Player 1" : "Current: Player 2");
        }
    }

    public Node getNode() {
        return node;
    }
}