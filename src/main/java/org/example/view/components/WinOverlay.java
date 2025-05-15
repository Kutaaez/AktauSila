package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.controller.GameController;
import org.example.domain.db.GameResultDAO;
import org.example.domain.player.BotPlayer;
import javafx.stage.Stage;
import org.example.view.screens.MainMenuView;

public class WinOverlay extends StackPane {
    private final Label resultLabel;
    private final Button newGameButton;
    private final Button mainMenuButton;
    private final GameController controller;

    public WinOverlay(GameController controller, Stage stage) {
        this.controller = controller;
        // Полупрозрачный фон, растягивается на весь родитель
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        setVisible(false); // Изначально скрыт
        setAlignment(Pos.CENTER); // Ensure it is centered

        // Контейнер для содержимого
        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #F9F0DF; -fx-background-radius: 10; -fx-padding: 20;");
        content.setMaxWidth(300); // Ограничение ширины
        content.setPrefWidth(300);

        // Метка результата
        resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1a1a1a; -fx-wrap-text: true; -fx-alignment: center;");
        resultLabel.setMaxWidth(260); // Ограничение ширины текста

        // Кнопка "Начать новую игру"
        newGameButton = new Button("Start New Game");
        newGameButton.setPrefWidth(150);
        newGameButton.getStyleClass().add("reset-button");
        newGameButton.setOnAction(e -> {
            controller.onNewGame();
            setVisible(false);
        });

        // Кнопка "На главную"
        mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefWidth(150);
        mainMenuButton.getStyleClass().add("exit-button");
        mainMenuButton.setOnAction(e -> {
            MainMenuView menuView = new MainMenuView(stage);
            stage.setScene(menuView.getScene());
            setVisible(false);
        });

        content.getChildren().addAll(resultLabel, newGameButton, mainMenuButton);
        getChildren().add(content);
    }

    public void showResult(int result) {
        boolean isBotGame = controller.getPlayers()[1] instanceof BotPlayer;
        if (result == 1) {

            GameResultDAO.saveGameResult(
                    "Player 1",
                    isBotGame ? "Single Player" : "Two Player"
            );

            resultLabel.setText(isBotGame ? "Player Wins!" : "Player 1 Wins!");
        } else if (result == -1) {

            GameResultDAO.saveGameResult(
                    isBotGame ? "Bot" : "Player 2",
                    isBotGame ? "Single Player" : "Two Player"
            );

            resultLabel.setText(isBotGame ? "Bot Wins!" : "Player 2 Wins!");
        } else if (result == 0) {

            GameResultDAO.saveGameResult(
                    "Draw",
                    isBotGame ? "Single Player" : "Two Player"
            );
            resultLabel.setText("Draw!");
        }
        setVisible(true);
    }
}