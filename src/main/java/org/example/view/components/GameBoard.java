package org.example.view.components;

import javafx.scene.layout.GridPane;
import org.example.controller.GameController;

public class GameBoard extends Component {
    private final GameController controller;
    private final HolePane[] holes;

    public GameBoard(GameController controller) {
        super(new GridPane());
        this.controller = controller;
        this.holes = new HolePane[18]; // Предполагается 9 лунок на игрока
        GridPane grid = (GridPane) container;
        grid.setHgap(10);
        grid.setVgap(10);
        applyStyleClass("game-board");

        // Создание лунок
        for (int i = 0; i < 9; i++) {
            holes[i] = new HolePane(i, true); // Лунки игрока 1
            holes[i + 9] = new HolePane(i, false); // Лунки игрока 2
            grid.add(holes[i].getNode(), i, 1);
            grid.add(holes[i + 9].getNode(), i, 0);
        }

        // Настройка событий для лунок
        for (int i = 0; i < 18; i++) {
            int index = i;
            holes[i].setOnMouseClicked(e -> {
                if (!controller.isPaused() && !controller.isFinished()) {
                    controller.makeMove(index);
                    update();
                }
            });
        }
    }

    public void update() {
        // Обновление состояния лунок
        for (int i = 0; i < 18; i++) {
            holes[i].setCount(controller.getHoleCount(i)); // Предполагаемый метод
            holes[i].setTuzdyk(controller.isTuzdyk(i)); // Предполагаемый метод
        }
    }
}