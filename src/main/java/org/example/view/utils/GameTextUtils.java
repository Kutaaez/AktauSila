package org.example.view.utils;

import org.example.controller.GameController;
import org.example.domain.player.BotPlayer;
import java.util.ResourceBundle;

public class GameTextUtils {
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages");

    public static String getResultText(int result, GameController controller) {
        boolean isBotGame = controller.getPlayers()[1] instanceof BotPlayer;
        if (result == 1) {
            return isBotGame ? MESSAGES.getString("win.player") : MESSAGES.getString("win.player1");
        } else if (result == -1) {
            return isBotGame ? MESSAGES.getString("win.bot") : MESSAGES.getString("win.player2");
        } else {
            return MESSAGES.getString("win.draw");
        }
    }

    public static String getPlayerName(int playerIndex, GameController controller) {
        boolean isBotGame = controller.getPlayers()[1] instanceof BotPlayer;
        if (playerIndex == 0) {
            return isBotGame ? MESSAGES.getString("player") : MESSAGES.getString("player1");
        } else {
            return isBotGame ? MESSAGES.getString("bot") : MESSAGES.getString("player2");
        }
    }

    public static String getExitConfirmText() {
        return MESSAGES.getString("exit.confirm");
    }

    public static String getNewGameButtonText() {
        return MESSAGES.getString("button.newGame");
    }

    public static String getMainMenuButtonText() {
        return MESSAGES.getString("button.mainMenu");
    }

    public static String getPauseButtonText() {
        return MESSAGES.getString("button.pause");
    }

    public static String getResetButtonText() {
        return MESSAGES.getString("button.reset");
    }

    public static String getExitButtonText() {
        return MESSAGES.getString("button.exit");
    }

    public static String getPauseConfirmText() {
        return MESSAGES.getString("pause.confirm");
    }

    public static String getResetConfirmText() {
        return MESSAGES.getString("reset.confirm");
    }
}