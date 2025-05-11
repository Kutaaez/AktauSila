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
import javafx.stage.StageStyle;

public class ConfirmModal {
    private final Stage stage;
    private Runnable onConfirm;

    public ConfirmModal(String title, String message) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle(title);

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));
        content.getStyleClass().add("modal-content");

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("modal-label");

        Button confirmButton = new Button("Confirm");
        confirmButton.setPrefWidth(100);
        confirmButton.getStyleClass().add("reset-button");
        confirmButton.setOnAction(e -> {
            if (onConfirm != null) {
                onConfirm.run();
            }
            stage.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.getStyleClass().add("exit-button");
        cancelButton.setOnAction(e -> stage.close());

        content.getChildren().addAll(messageLabel, confirmButton, cancelButton);

        StackPane root = new StackPane(content);
        root.getStyleClass().add("modal-background");

        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
    }

    public void setOnConfirm(Runnable onConfirm) {
        this.onConfirm = onConfirm;
    }

    public void show() {
        stage.showAndWait();
    }
}