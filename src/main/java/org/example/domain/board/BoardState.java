package org.example.domain.board;

public class BoardState {
    private final Holes holes;
    private final Kazans kazans;
    private final Tuzdyks tuzdyks;
    private final CurrentPlayer currentPlayer;

    public BoardState() {
        this.holes = new Holes();
        this.kazans = new Kazans();
        this.tuzdyks = new Tuzdyks();
        this.currentPlayer = new CurrentPlayer();
    }

    public Holes getHoles() {
        return holes;
    }

    public Kazans getKazans() {
        return kazans;
    }

    public Tuzdyks getTuzdyks() {
        return tuzdyks;
    }

    public CurrentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public int getHoleCount(int index) {
        return holes.getSeedCount(index);
    }

    public int getKazan(int playerColor) {
        return kazans.getKazan(playerColor);
    }

    public void addToKazan(int playerColor, int seeds) {
        kazans.addToKazan(playerColor, seeds);
    }

    public void reset() {
        holes.reset();
        kazans.reset();
        tuzdyks.reset();
        currentPlayer.reset();
    }
}

//facade
//It wraps the board’s underlying components and provides a single,
// simplified interface for querying and modifying the game state.