package org.example.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.view.components.ConfirmModal;
import org.example.view.components.GameHistory;

public class MainMenuView {
    private final Stage stage;
    private final Scene scene;

    public MainMenuView(Stage stage) {
        this.stage = stage;

        // Root layout
        VBox root = new VBox(20);
        root.getStyleClass().add("root");
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        // Title
        Label titleLabel = new Label("Toguz Qumalaq");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #1a1a1a;");

        // Buttons
        Button singlePlayerButton = new Button("Single Player");
        singlePlayerButton.getStyleClass().add("reset-button");
        singlePlayerButton.setPrefWidth(200);
        singlePlayerButton.setOnAction(e -> startGame(false));

        Button twoPlayerButton = new Button("Two Players");
        twoPlayerButton.getStyleClass().add("reset-button");
        twoPlayerButton.setPrefWidth(200);
        twoPlayerButton.setOnAction(e -> startGame(true));

        Button historyButton = new Button("Game History");
        historyButton.getStyleClass().add("reset-button");
        historyButton.setPrefWidth(200);
        historyButton.setOnAction(e -> {
            GameController controller = new GameController(false); // Initialize with default single-player mode
            GameHistory gameHistory = new GameHistory(stage, controller);
        });

        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("exit-button");
        exitButton.setPrefWidth(200);
        exitButton.setOnAction(e -> {
            ConfirmModal modal = new ConfirmModal("Exit Game", "Are you sure you want to exit?");
            modal.setOnConfirm(() -> stage.close());
            modal.show();
        });

        // Assemble
        root.getChildren().addAll(titleLabel, singlePlayerButton, twoPlayerButton, historyButton, exitButton);

        // Scene setup
        scene = new Scene(root, 400, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    private void startGame(boolean twoPlayers) {
        GameController controller = new GameController(twoPlayers);
        MainView mainView = new MainView(controller, stage);
        stage.setScene(mainView.getScene());
    }

    public Scene getScene() {
        return scene;
    }
}