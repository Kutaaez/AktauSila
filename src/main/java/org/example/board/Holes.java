package org.example.board;

public class Holes {
    public static final int TOTAL_HOLES = 18;
    public static final int HOLES_PER_PLAYER = 9;
    public static final int INITIAL_SEEDS = 9;
    public static final int TUZDYK_MARKER = 255;
    private final int[] holes;

    public Holes() {
        holes = new int[TOTAL_HOLES];
        reset();
    }

    public int getSeedCount(int index) {
        validateIndex(index);
        return holes[index];
    }

    public void setSeedCount(int index, int count) {
        validateIndex(index);
        holes[index] = count;
    }
}
