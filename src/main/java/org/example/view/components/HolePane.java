package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
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
    private int ballCount;

    public HolePane(int holeIndex, boolean playerSide) {
        this.holeIndex = holeIndex;
        this.playerSide = playerSide;
        this.isTuzdyk = false;
        this.ballCount = 0;

        container = new StackPane();
        container.setPrefSize(140, 120);
        container.setMaxSize(140, 120);
        container.setPadding(new Insets(10));
        container.getStyleClass().add("hole-pane");
        container.getStyleClass().add(playerSide ? "player" : "opponent");

        background = new Rectangle(120, 100);
        background.setArcWidth(16);
        background.setArcHeight(16);
        background.getStyleClass().add("hole-background");

        ballsGrid = new GridPane();
        ballsGrid.setAlignment(Pos.CENTER);
        ballsGrid.setHgap(6);
        ballsGrid.setVgap(6);

        stackedBalls = new StackPane();
        stackedBalls.setAlignment(Pos.CENTER);

        countLabel = new Label("0");
        countLabel.getStyleClass().add("hole-count-label");
        StackPane.setAlignment(countLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(countLabel, new Insets(0, 0, 5, 0));

        indexLabel = new Label(String.valueOf(holeIndex + 1));
        indexLabel.getStyleClass().add("hole-index-label");
        StackPane.setAlignment(indexLabel, Pos.TOP_CENTER);
        StackPane.setMargin(indexLabel, new Insets(5, 0, 0, 0));

        container.getChildren().addAll(background, ballsGrid, stackedBalls, countLabel, indexLabel);
        getChildren().add(container);

        ballDisplayManager = new BallDisplayManager(ballsGrid, stackedBalls, countLabel);
        animationHandler = new AnimationHandler(ballsGrid, stackedBalls);

        setOnMousePressed(event -> {
            if (!isDisabled()) {
                animationHandler.handleMousePressed(false);
            }
        });
        setOnMouseReleased(event -> {
            if (!isDisabled()) {
                animationHandler.handleMouseReleased(false);
            }
        });
    }

    public int getHoleIndex() {
        return holeIndex;
    }

    public void setCount(int count) {
        this.ballCount = count;
        countLabel.setText(String.valueOf(count));
        ballDisplayManager.updateDisplay(count, isTuzdyk);
    }

    public void setTuzdyk(boolean isTuzdyk) {
        this.isTuzdyk = isTuzdyk;
        container.getStyleClass().remove("red-tuzdyk");
        if (isTuzdyk) {
            container.getStyleClass().add("red-tuzdyk");
        }
        ballDisplayManager.updateDisplay(ballCount, isTuzdyk);
    }

    public void setDisabled(boolean disabled) {
        super.setDisable(disabled);
    }
}