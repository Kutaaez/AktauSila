package org.example.patterns;

import org.example.game.Ball;
import org.example.game.Board;
import org.example.patterns.factory.GameComponentFactory;

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

