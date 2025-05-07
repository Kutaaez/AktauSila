package org.example.view.components;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.view.utils.GameTextUtils;
import org.example.view.utils.MenuActionHandler;

public class WinOverlay extends StackPane {
    private final ResultPanel resultPanel;
    private final MenuActionHandler actionHandler;

    public WinOverlay(GameController controller, Stage stage) {
        getStyleClass().add("win-overlay");
        setVisible(false);
        setAlignment(Pos.CENTER);

        this.resultPanel = new ResultPanel();
        this.actionHandler = new MenuActionHandler(stage, controller, this);

        resultPanel.getNewGameButton().setOnAction(e -> actionHandler.handleNewGame());
        resultPanel.getMainMenuButton().setOnAction(e -> actionHandler.handleMainMenu());

        getChildren().add(resultPanel.getNode());
    }

    public void showResult(int result) {
        resultPanel.getResultLabel().setText(GameTextUtils.getResultText(result, controller));
        setVisible(true);
    }
}