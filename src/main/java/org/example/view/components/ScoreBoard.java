package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScoreBoard extends Component {
    private final Label playerScore;
    private final Label opponentScore;

    public ScoreBoard(String playerName, String opponentName) {
        super(new VBox(10));
        applyStyleClass("score-board");

        playerScore = createScoreLabel();
        opponentScore = createScoreLabel();

        HBox playerBox = createScoreBox(playerName + ":", playerScore);
        HBox opponentBox = createScoreBox(opponentName + ":", opponentScore);

        ((VBox) container).getChildren().addAll(playerBox, opponentBox);
    }

    private Label createScoreLabel() {
        Label label = new Label("0");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1a1a1a;");
        label.setPadding(new Insets(5));
        return label;
    }

    private HBox createScoreBox(String title, Label score) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
        HBox box = new HBox(5, titleLabel, score);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    public void setScores(int playerScore, int opponentScore) {
        this.playerScore.setText(String.valueOf(playerScore));
        this.opponentScore.setText(String.valueOf(opponentScore));
    }
}