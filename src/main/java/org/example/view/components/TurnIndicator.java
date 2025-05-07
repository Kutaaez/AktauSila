package org.example.view.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TurnIndicator extends Component {
    private final Label label;
    private final String playerName;
    private final String opponentName;

    public TurnIndicator(String playerName, String opponentName) {
        super(new HBox());
        this.playerName = playerName;
        this.opponentName = opponentName;
        applyStyleClass("turn-indicator");

        label = new Label(playerName + "'s Turn");
        label.getStyleClass().add("turn-label");
        ((HBox) container).getChildren().add(label);
    }

    public void setCurrentPlayer(int player) {
        if (player == 0) {
            label.setText(playerName + "'s Turn");
            label.getStyleClass().remove("opponent-turn");
            label.getStyleClass().add("player-turn");
        } else {
            label.setText(opponentName + "'s Turn");
            label.getStyleClass().remove("player-turn");
            label.getStyleClass().add("opponent-turn");
        }
    }
}