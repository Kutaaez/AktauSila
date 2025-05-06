package org.example.view.components;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;

public class BallDisplayManager {
    private final GridPane ballsGrid;
    private final StackPane stackedBalls;
    private final Label countLabel;

    public BallDisplayManager(GridPane ballsGrid, StackPane stackedBalls, Label countLabel) {
        this.ballsGrid = ballsGrid;
        this.stackedBalls = stackedBalls;
        this.countLabel = countLabel;
    }

    public void updateDisplay(int count, boolean isTuzdyk) {
        countLabel.setText(isTuzdyk ? "" : String.valueOf(count));
        ballsGrid.getChildren().clear();
        stackedBalls.getChildren().clear();

        if (isTuzdyk) {
            Circle redBall = new Circle(10);
            redBall.getStyleClass().add("red-ball");
            ballsGrid.add(redBall, 0, 0);
            return;
        }

        if (count == 255) {
            Circle redBall = new Circle(10);
            redBall.getStyleClass().add("red-ball");
            ballsGrid.add(redBall, 0, 0);
        } else {
            int gridCount = Math.min(count, 10);
            for (int i = 0; i < gridCount; i++) {
                Circle ball = new Circle(10);
                ball.getStyleClass().add("ball");
                int row = i / 5;
                int col = i % 5;
                ballsGrid.add(ball, col, row);
            }
            // No stacked balls for counts > 10
        }
    }
}