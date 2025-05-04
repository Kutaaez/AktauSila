package org.example.patterns.factory;

import org.example.game.Ball;
import org.example.game.Board;

public class DefaultGameFactory implements GameComponentFactory {

    @Override
    public Board createBoard() {
        return new Board();
    }

    @Override
    public Ball createBall() {
        return new Ball();
    }
}

