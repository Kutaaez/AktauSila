package org.example.view.screens;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.view.components.MenuPanel;
import org.example.view.utils.MenuActionHandler;

public class MainMenuView {
    private final Stage stage;
    private final Scene scene;
    private final MenuPanel menuPanel;
    private final MenuActionHandler actionHandler;

    public MainMenuView(Stage stage) {
        this.stage = stage;
        this.menuPanel = new MenuPanel();
        this.actionHandler = new MenuActionHandler(stage);

        menuPanel.getSinglePlayerButton().setOnAction(e -> actionHandler.handleSinglePlayer());
        menuPanel.getTwoPlayerButton().setOnAction(e -> actionHandler.handleTwoPlayers());
        menuPanel.getExitButton().setOnAction(e -> actionHandler.handleExit());

        scene = new Scene((Parent) menuPanel.getNode(), 400, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}