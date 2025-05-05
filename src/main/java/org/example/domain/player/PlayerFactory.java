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

//factory design pattern
//The PlayerFactory class implements the Factory design pattern
// by creating and returning different combinations of IPlayer objects
// (either two human players or one human and one bot) based on a boolean flag.