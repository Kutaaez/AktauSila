package org.example.patterns.factory;

import org.example.game.Ball;
import org.example.game.Board;

public interface GameComponentFactory {
    Board createBoard();
    Ball createBall();
}
