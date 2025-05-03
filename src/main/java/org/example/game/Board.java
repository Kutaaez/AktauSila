package org.example.game;

public class Board {
    private final int width;
    private final int height;
    private final Ball ball;

    public Board(int width, int height, Ball ball) {
        this.width  = width;
        this.height = height;
        this.ball   = ball;
    }
        //update method for future, but now it's like zaglushka
    public void update() {
        ball.move();
    }
    // < getter > \\
    public Ball getBall() {
        return ball;
    }
}
