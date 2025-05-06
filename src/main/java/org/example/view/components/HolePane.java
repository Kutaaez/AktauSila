package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;

public class HolePane extends Pane {
    private final int holeIndex;
    private final boolean playerSide;
    private final StackPane container;
    private final Rectangle background;
    private final GridPane ballsGrid;
    private final StackPane stackedBalls;
    private final Label countLabel;
    private final Label indexLabel;
    private final BallDisplayManager ballDisplayManager;
    private final AnimationHandler animationHandler;
    private boolean isTuzdyk;

    public HolePane(int holeIndex, boolean playerSide) {
        this.holeIndex = holeIndex;
        this.playerSide = playerSide;
        this.isTuzdyk = false;

        // Main container
        container = new StackPane();
        container.setPrefSize(140, 120);
        container.setMaxSize(140, 120);
        container.setPadding(new Insets(10));
        container.getStyleClass().add("hole-pane");
        container.getStyleClass().add(playerSide ? "player" : "opponent");

        // Rectangular background
        background = new Rectangle(120, 100);
        background.setArcWidth(16);
        background.setArcHeight(16);
        background.setFill(Color.web("#F9F0DF"));
        background.getStyleClass().add("hole-background");

        // Balls grid (2x5)
        ballsGrid = new GridPane();
        ballsGrid.setAlignment(Pos.CENTER);
        ballsGrid.setHgap(6);
        ballsGrid.setVgap(6);

        // Stacked balls for counts >= 11
        stackedBalls = new StackPane();
        stackedBalls.setAlignment(Pos.CENTER);

        // Count label
        countLabel = new Label("0");
        countLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        countLabel.setTextFill(Color.BLUE);
        StackPane.setAlignment(countLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(countLabel, new Insets(0, 0, 5, 0));

        // Index label
        indexLabel = new Label(String.valueOf(holeIndex + 1));
        indexLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        indexLabel.setTextFill(Color.BLACK);
        StackPane.setAlignment(indexLabel, Pos.TOP_CENTER);
        StackPane.setMargin(indexLabel, new Insets(5, 0, 0, 0));

        // Assemble
        container.getChildren().addAll(background, ballsGrid, stackedBalls, countLabel, indexLabel);
        getChildren().add(container);

        // Initialize helpers
        ballDisplayManager = new BallDisplayManager(ballsGrid, stackedBalls, countLabel);
        animationHandler = new AnimationHandler(ballsGrid, stackedBalls);

        // Set up animations
        setOnMousePressed(event -> animationHandler.handleMousePressed(isDisabled()));
        setOnMouseReleased(event -> animationHandler.handleMouseReleased(isDisabled()));
    }

    public int getHoleIndex() {
        return holeIndex;
    }

    public void setCount(int count) {
        ballDisplayManager.updateDisplay(count, isTuzdyk);
    }

    public void setTuzdyk(boolean isTuz) {
        this.isTuzdyk = isTuz;
        container.getStyleClass().remove("red-tuzdyk");
        if (isTuz) {
            container.getStyleClass().add("red-tuzdyk");
        }
        ballDisplayManager.updateDisplay(Integer.parseInt(countLabel.getText().isEmpty() ? "0" : countLabel.getText()), isTuz);
    }
}