package org.example.view.components;

import javafx.animation.ScaleTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class AnimationHandler {
    private final GridPane ballsGrid;
    private final StackPane stackedBalls;

    public AnimationHandler(GridPane ballsGrid, StackPane stackedBalls) {
        this.ballsGrid = ballsGrid;
        this.stackedBalls = stackedBalls;
    }

    public void handleMousePressed(boolean isDisabled) {
        if (!isDisabled) {
            ScaleTransition scale = new ScaleTransition(Duration.millis(10), ballsGrid);
            scale.setToX(1.1);
            scale.setToY(1.1);
            scale.play();
            ScaleTransition scaleStacked = new ScaleTransition(Duration.millis(10), stackedBalls);
            scaleStacked.setToX(1.1);
            scaleStacked.setToY(1.1);
            scaleStacked.play();
        }
    }

    public void handleMouseReleased(boolean isDisabled) {
        if (!isDisabled) {
            ScaleTransition scale = new ScaleTransition(Duration.millis(10), ballsGrid);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.play();
            ScaleTransition scaleStacked = new ScaleTransition(Duration.millis(10), stackedBalls);
            scaleStacked.setToX(1.0);
            scaleStacked.setToY(1.0);
            scaleStacked.play();
        }
    }
}