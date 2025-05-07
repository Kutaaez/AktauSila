package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmModal extends StackPane {
    private static final double MODAL_WIDTH = 300;
    private static final double MODAL_HEIGHT = 200;

    private final Stage modalStage;
    private Runnable onConfirm;
    private Runnable onMainMenu;

    public ConfirmModal(String title, String message) {
        this.modalStage = new Stage();
        this.modalStage.initModality(Modality.APPLICATION_MODAL);
        getStyleClass().add("modal-background");

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));
        content.getStyleClass().add("modal-content");

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("modal-label");

        Button confirmButton = new Button("Yes");
        confirmButton.getStyleClass().add("reset-button");
        confirmButton.setOnAction(e -> {
            if (onConfirm != null) {
                onConfirm.run();
            }
            modalStage.close();
        });

        Button cancelButton = new Button("No");
        cancelButton.getStyleClass().add("exit-button");
        cancelButton.setOnAction(e -> modalStage.close());

        Button mainMenuButton = new Button(GameTextUtils.getMainMenuButtonText());
        mainMenuButton.getStyleClass().add("exit-button");
        mainMenuButton.setOnAction(e -> {
            if (onMainMenu != null) {
                onMainMenu.run();
            }
            modalStage.close();
        });

        content.getChildren().addAll(messageLabel, confirmButton, cancelButton, mainMenuButton);
        getChildren().add(content);

        modalStage.setScene(new Scene(this, MODAL_WIDTH, MODAL_HEIGHT));
        modalStage.setTitle(title);
    }

    public void setOnConfirm(Runnable onConfirm) {
        this.onConfirm = onConfirm;
    }

    public void setOnMainMenu(Runnable onMainMenu) {
        this.onMainMenu = onMainMenu;
    }

    public void show() {
        modalStage.showAndWait();
    }
}