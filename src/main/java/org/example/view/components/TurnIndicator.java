package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TurnIndicator {
    private final HBox container;
    private final Label label;

    public TurnIndicator() {
        container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(10));
        container.getStyleClass().add("turn-indicator");

        label = new Label("Ход игрока");
        label.getStyleClass().add("turn-label");
        container.getChildren().add(label);
    }

    public void setCurrentPlayer(int player) {
        if (player == 0) {
            label.setText("Ход игрока");
            label.getStyleClass().remove("opponent-turn");
            label.getStyleClass().add("player-turn");
        } else {
            label.setText("Ход противника");
            label.getStyleClass().remove("player-turn");
            label.getStyleClass().add("opponent-turn");
        }
    }

    public Node getNode() {
        return container;
    }
}