package org.example.view.utils;

import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.view.components.ConfirmModal;
import org.example.view.screens.MainMenuView;
import org.example.view.components.WinOverlay;

public class MenuActionHandler {
    private final Stage stage;
    private final GameController controller;
    private final WinOverlay winOverlay;

    public MenuActionHandler(Stage stage, GameController controller, WinOverlay winOverlay) {
        this.stage = stage;
        this.controller = controller;
        this.winOverlay = winOverlay;
    }

    public MenuActionHandler(Stage stage) {
        this(stage, null, null);
    }

    public void handleSinglePlayer() {
        startGame(false);
    }

    public void handleTwoPlayers() {
        startGame(true);
    }

    public void handleExit() {
        ConfirmModal modal = new ConfirmModal("Exit Game", GameTextUtils.getExitConfirmText());
        modal.setOnConfirm(() -> stage.close());
        modal.show();
    }

    public void handleNewGame() {
        if (controller != null) {
            controller.onNewGame();
        }
        if (winOverlay != null) {
            winOverlay.setVisible(false);
        }
    }

    public void handleMainMenu() {
        MainMenuView menuView = new MainMenuView(stage);
        stage.setScene(menuView.getScene());
        if (winOverlay != null) {
            winOverlay.setVisible(false);
        }
    }

    public void handlePause() {
        ConfirmModal modal = new ConfirmModal("Game Paused", GameTextUtils.getPauseConfirmText());
        modal.setOnConfirm(() -> controller.resumeGame());
        modal.setOnMainMenu(() -> handleMainMenu());
        modal.show();
    }

    private void startGame(boolean twoPlayers) {
        GameController controller = new GameController(twoPlayers);
        MainMenuView mainView = new MainMenuView(stage);
        stage.setScene(mainView.getScene());
    }
}