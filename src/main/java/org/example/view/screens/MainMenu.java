package org.example.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.view.components.*;

public class MainView {
    private final GameController controller;
    private final Stage stage;
    private final Scene scene;
    private final BorderPane root;
    private final HBox topRow;
    private final HBox bottomRow;
    private final ScoreBoard scoreBoard;
    private final PlayerPanel playerPanel;
    private final WinOverlay winOverlay;

    public MainView(GameController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        this.root = new BorderPane();
        this.topRow = new HBox(10);
        this.bottomRow = new HBox(10);
        this.scoreBoard = new ScoreBoard(controller);
        this.playerPanel = new PlayerPanel(controller);
        this.winOverlay = new WinOverlay(controller, stage);
        buildLayout();
        this.scene = new Scene(root, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        controller.setView(this);

        // Center the stage and make it non-resizable
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    private void buildLayout() {
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        // Top row — opponent's holes
        topRow.setAlignment(Pos.CENTER);
        topRow.setPadding(new Insets(15));
        for (int i = 1; i <= 9; i++) {
            HolePane pane = new HolePane(i, false);
            pane.setOnMouseClicked(e -> controller.onHoleClicked(pane.getHoleIndex(), false));
            topRow.getChildren().add(pane);
        }
        root.setTop(topRow);

        // Bottom row — player's holes
        bottomRow.setAlignment(Pos.CENTER);
        bottomRow.setPadding(new Insets(15));
        for (int i = 1; i <= 9; i++) {
            HolePane pane = new HolePane(i, true);
            pane.setOnMouseClicked(e -> controller.onHoleClicked(pane.getHoleIndex(), true));
            bottomRow.getChildren().add(pane);
        }
        root.setBottom(bottomRow);

        // Center — scoreboard and winOverlay
        StackPane centerStack = new StackPane();
        VBox centerBox = new VBox(15);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(scoreBoard.getNode());
        centerStack.getChildren().addAll(centerBox, winOverlay);
        StackPane.setAlignment(centerBox, Pos.CENTER);
        StackPane.setAlignment(winOverlay, Pos.CENTER);
        winOverlay.setVisible(false);
        root.setCenter(centerStack);

        // Right — player panel and buttons
        VBox rightBox = new VBox(15);
        rightBox.setAlignment(Pos.TOP_CENTER);
        rightBox.setPadding(new Insets(15));
        rightBox.getChildren().add(playerPanel.getNode());

        Button resetBtn = new Button("Reset");
        resetBtn.setPrefWidth(120);
        resetBtn.getStyleClass().add("reset-button");
        resetBtn.setOnAction(e -> {
            ConfirmModal modal = new ConfirmModal("Start New Game", "Are you sure you want\n to start a new game?");
            modal.setOnConfirm(() -> controller.onNewGame());
            modal.show();
        });

        Button exitBtn = new Button("Exit");
        exitBtn.setPrefWidth(120);
        exitBtn.getStyleClass().add("exit-button");
        exitBtn.setOnAction(e -> {
            ConfirmModal modal = new ConfirmModal("Exit Game", "Are you sure you want to exit\n to the main menu?");
            modal.setOnConfirm(() -> {
                MainMenuView menuView = new MainMenuView(stage);
                stage.setScene(menuView.getScene());
            });
            modal.show();
        });

        rightBox.getChildren().addAll(resetBtn, exitBtn);
        root.setRight(rightBox);
    }

    public void update() {
        int currentPlayer = controller.getCurrentPlayer();
        for (Node node : topRow.getChildren()) {
            HolePane hp = (HolePane) node;
            hp.setTuzdyk(false); // Reset tuzdyk state
            hp.setCount(controller.getOpponentHoleCount(hp.getHoleIndex()));
            hp.setTuzdyk(controller.getTuzdyk(0) == hp.getHoleIndex());
            hp.setDisable(currentPlayer != 1);
        }
        for (Node node : bottomRow.getChildren()) {
            HolePane hp = (HolePane) node;
            hp.setTuzdyk(false); // Reset tuzdyk state
            hp.setCount(controller.getHoleCount(hp.getHoleIndex()));
            hp.setTuzdyk(controller.getTuzdyk(1) == hp.getHoleIndex());
            hp.setDisable(currentPlayer != 0);
        }
        scoreBoard.setScores(controller.getKazan(0), controller.getKazan(1));
        playerPanel.setScores(controller.getKazan(0), controller.getKazan(1));
        playerPanel.setCurrentPlayer(currentPlayer);

        if (controller.isFinished()) {
            winOverlay.showResult(controller.getGameResult());
            winOverlay.setVisible(true); // Explicitly ensure visibility
            StackPane.setAlignment(winOverlay, Pos.CENTER); // Reinforce centering
        } else {
            winOverlay.setVisible(false);
        }
    }

    public Scene getScene() {
        return scene;
    }
}