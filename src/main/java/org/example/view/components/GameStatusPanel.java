package org.example.view.components;

import javafx.scene.layout.VBox;
import org.example.controller.GameController;
import org.example.domain.player.BotPlayer;

public class GameStatusPanel extends Component {
    private final ScoreBoard scoreBoard;
    private final TurnIndicator turnIndicator;

    public GameStatusPanel(GameController controller) {
        super(new VBox(10));
        applyStyleClass("game-status-panel");

        String playerName = "Player 1";
        String opponentName = controller.getPlayers()[1] instanceof BotPlayer ? "Bot" : "Player 2";

        scoreBoard = new ScoreBoard(playerName, opponentName);
        turnIndicator = new TurnIndicator(playerName, opponentName);

        ((VBox) container).getChildren().addAll(scoreBoard.getNode(), turnIndicator.getNode());
    }

    public void setScores(int playerScore, int opponentScore) {
        scoreBoard.setScores(playerScore, opponentScore);
    }

    public void setCurrentPlayer(int player) {
        turnIndicator.setCurrentPlayer(player);
    }
}