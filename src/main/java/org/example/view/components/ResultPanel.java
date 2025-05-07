package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ResultPanel extends Component {
    private static final double CONTENT_WIDTH = 300;
    private static final double LABEL_MAX_WIDTH = 260;
    private static final double BUTTON_WIDTH = 150;

    private final Label resultLabel;
    private final Button newGameButton;
    private final Button mainMenuButton;

    public ResultPanel() {
        super(new VBox(20));
        ((VBox) container).setAlignment(Pos.CENTER);
        ((VBox) container).setPadding(new Insets(20));
        ((VBox) container).setMaxWidth(CONTENT_WIDTH);
        ((VBox) container).setPrefWidth(CONTENT_WIDTH);
        applyStyleClass("win-overlay-content");

        resultLabel = new Label();
        resultLabel.getStyleClass().add("win-overlay-label");
        resultLabel.setMaxWidth(LABEL_MAX_WIDTH);

        newGameButton = createButton("Start New Game", "reset-button");
        mainMenuButton = createButton("Main Menu", "exit-button");

        ((VBox) container).getChildren().addAll(resultLabel, newGameButton, mainMenuButton);
    }

    private Button createButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        button.setPrefWidth(BUTTON_WIDTH);
        return button;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public Button getNewGameButton() {
        return newGameButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }
}