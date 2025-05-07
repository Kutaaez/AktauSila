package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuPanel extends Component {
    private final Label titleLabel;
    private final Button singlePlayerButton;
    private final Button twoPlayerButton;
    private final Button exitButton;

    public MenuPanel() {
        super(new VBox(20));
        ((VBox) container).setAlignment(Pos.CENTER);
        ((VBox) container).setPadding(new Insets(20));
        applyStyleClass("menu-panel");

        titleLabel = new Label("Toguz Kumalak");
        titleLabel.getStyleClass().add("menu-title");

        singlePlayerButton = createButton("Single Player", "reset-button");
        twoPlayerButton = createButton("Two Players", "reset-button");
        exitButton = createButton("Exit", "exit-button");

        ((VBox) container).getChildren().addAll(titleLabel, singlePlayerButton, twoPlayerButton, exitButton);
    }

    private Button createButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        button.setPrefWidth(200);
        return button;
    }

    public Button getSinglePlayerButton() {
        return singlePlayerButton;
    }

    public Button getTwoPlayerButton() {
        return twoPlayerButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}