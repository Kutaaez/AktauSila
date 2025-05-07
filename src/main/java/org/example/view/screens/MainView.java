package org.example.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.view.components.ControlPanel;
import org.example.view.components.GameBoard;
import org.example.view.components.WinOverlay;

public class MainView {
    private final GameController controller;
    private final Stage stage;
    private final Scene scene;
    private final BorderPane root;
    private final GameBoard gameBoard;
    private final ControlPanel controlPanel;
    private final WinOverlay winOverlay;

    public MainView(GameController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        this.root = new BorderPane();
        this.gameBoard = new GameBoard(controller);
        this.controlPanel = new ControlPanel(controller, stage); // Исправлена опечатка
        this.winOverlay = new WinOverlay(controller, stage);
        buildLayout();
        this.scene = new Scene(root, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        controller.setView(this);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    private void buildLayout() {
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        root.setTop(gameBoard.getNode());
        root.setRight(controlPanel.getNode());

        StackPane centerStack = new StackPane();
        centerStack.getChildren().add(winOverlay);
        StackPane.setAlignment(winOverlay, Pos.CENTER);
        winOverlay.setVisible(false);
        root.setCenter(centerStack);
    }

    public void update() {
        gameBoard.update();
        controlPanel.getStatusPanel().setScores(controller.getKazan(0), controller.getKazan(1));
        controlPanel.getStatusPanel().setCurrentPlayer(controller.getCurrentPlayer());

        if (controller.isFinished()) {
            winOverlay.showResult(controller.getGameResult());
            winOverlay.setVisible(true);
            StackPane.setAlignment(winOverlay, Pos.CENTER);
        } else {
            winOverlay.setVisible(false);
        }
    }

    public Scene getScene() {
        return scene;
    }
}