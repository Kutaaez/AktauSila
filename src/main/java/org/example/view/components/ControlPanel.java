package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.view.screens.MainMenuView;
import org.example.view.utils.GameTextUtils;

public class ControlPanel extends Component {
    private final GameController controller;
    private final Stage stage;
    private final GameStatusPanel statusPanel;
    private final Button pauseButton;

    public ControlPanel(GameController controller, Stage stage) {
        super(new VBox(15));
        this.controller = controller;
        this.stage = stage;
        this.statusPanel = new GameStatusPanel(controller);
        applyStyleClass("control-panel");

        pauseButton = createButton(GameTextUtils.getPauseButtonText(), "pause-button", () -> controller.pauseGame());

        Button resetBtn = createButton(GameTextUtils.getResetButtonText(), "reset-button", () -> {
            ConfirmModal modal = new ConfirmModal("Start New Game", GameTextUtils.getResetConfirmText());
            modal.setOnConfirm(controller::onNewGame);
            modal.show();
        });

        Button exitBtn = createButton(GameTextUtils.getExitButtonText(), "exit-button", () -> {
            ConfirmModal modal = new ConfirmModal("Exit Game", GameTextUtils.getExitConfirmText());
            modal.setOnConfirm(() -> {
                MainMenuView menuView = new MainMenuView(stage);
                stage.setScene(menuView.getScene());
            });
            modal.show();
        });

        ((VBox) container).setAlignment(Pos.TOP_CENTER);
        ((VBox) container).setPadding(new Insets(15));
        ((VBox) container).getChildren().addAll(statusPanel.getNode(), pauseButton, resetBtn, exitBtn);
    }

    private Button createButton(String text, String styleClass, Runnable onAction) {
        Button button = new Button(text);
        button.setPrefWidth(120);
        button.getStyleClass().add(styleClass);
        button.setOnAction(e -> onAction.run());
        return button;
    }

    public GameStatusPanel getStatusPanel() {
        return statusPanel;
    }

    public Button getPauseButton() {
        return pauseButton;
    }
}