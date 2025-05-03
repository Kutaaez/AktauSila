package org.example.game;




public class Ball {
    private double x, y;
    private double vx, vy;
    private final double radius;

    public Ball(double x, double y, double radius, double vx, double vy) {
        this.x      = x;
        this.y      = y;
        this.radius = radius;
        this.vx     = vx;
        this.vy     = vy;
    }

    // method for move ball
    public void move() {
        x += vx;
        y += vy;
    }

    // < getters / setters > \\
    public double getX()       { return x; }
    public double getY()       { return y; }
    public double getRadius()  { return radius; }
    public void setVx(double v){ this.vx = v; }
    public void setVy(double v){ this.vy = v; }
}
