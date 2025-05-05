package org.example.domain.player;

public class PlayerFactory {
    public static IPlayer[] createPlayers(boolean twoPlayers) {
        if (twoPlayers) {
            return new IPlayer[]{new HumanPlayer(0), new HumanPlayer(1)};
        } else {
            return new IPlayer[]{new HumanPlayer(0), new BotPlayer(1)};
        }
    }
}