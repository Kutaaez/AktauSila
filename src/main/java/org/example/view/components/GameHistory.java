package org.example.view.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.domain.db.GameResult;
import org.example.domain.db.GameResultDAO;
import org.example.view.screens.MainMenuView;

import java.util.List;

public class GameHistory {
    private final Stage stage;
    private final GameController gameController;

    public GameHistory(Stage stage, GameController gameController) {
        this.stage = stage;
        this.gameController = gameController;
        initialize();
    }

    private void initialize() {
        // Main layout
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.web("#F5DEB3"), null, null)));
        root.getStyleClass().add("root");

        // Title
        Label title = new Label("Game History");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setStyle("-fx-text-fill: #1A1A1A;");

        // TableView container for consistent styling
        VBox tableContainer = new VBox();
        tableContainer.setBackground(new Background(new BackgroundFill(Color.web("#F9F0DF"), null, null)));
        tableContainer.setPadding(new Insets(10));
        tableContainer.setStyle("-fx-border-color: #8B4513; -fx-border-width: 2px; -fx-border-radius: 8px;");

        // TableView for game results
        TableView<GameResult> tableView = new TableView<>();
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(400);
        tableView.setStyle("-fx-background-color: #F9F0DF; -fx-control-inner-background: #F9F0DF; -fx-border-color: transparent;");

        // Define columns
        TableColumn<GameResult, String> winnerColumn = new TableColumn<>("Winner");
        winnerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWinner()));
        winnerColumn.setPrefWidth(150);
        winnerColumn.setStyle("-fx-alignment: CENTER-LEFT; -fx-text-fill: #1A1A1A; -fx-font-family: 'Arial';");

        TableColumn<GameResult, String> gameModeColumn = new TableColumn<>("Game Mode");
        gameModeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGameMode()));
        gameModeColumn.setPrefWidth(150);
        gameModeColumn.setStyle("-fx-alignment: CENTER-LEFT; -fx-text-fill: #1A1A1A; -fx-font-family: 'Arial';");

        TableColumn<GameResult, String> timestampColumn = new TableColumn<>("Timestamp");
        timestampColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimestamp().toString()));
        timestampColumn.setPrefWidth(300);
        timestampColumn.setStyle("-fx-alignment: CENTER-LEFT; -fx-text-fill: #1A1A1A; -fx-font-family: 'Arial';");

        tableView.getColumns().addAll(winnerColumn, gameModeColumn, timestampColumn);

        // Fetch data from DAO
        List<GameResult> results = GameResultDAO.fetchAll();
        ObservableList<GameResult> data = FXCollections.observableArrayList(results);
        tableView.setItems(data);

        // Style table rows
        tableView.setRowFactory(tv -> {
            javafx.scene.control.TableRow<GameResult> row = new javafx.scene.control.TableRow<>();
            row.setStyle("-fx-background-color: #F9F0DF; -fx-text-fill: #1A1A1A; -fx-font-family: 'Arial';");
            return row;
        });

        // Add TableView to container
        tableContainer.getChildren().add(tableView);

        // Back button
        Button backButton = new Button("← Back to Menu");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backButton.setPrefWidth(200);
        backButton.getStyleClass().add("reset-button");
        backButton.setOnAction(e -> {
            MainMenuView menuView = new MainMenuView(stage);
            stage.setScene(menuView.getScene());
        });

        // Add components to layout
        root.getChildren().addAll(title, tableContainer, backButton);

        // Set up scene
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Game History");
        stage.show();
    }
}