package org.example.board;

public class Tuzdyks {
    public static final int WHITE_TUZDYK = 0;
    public static final int BLACK_TUZDYK = 1;

    private final int[] tuzdyks;
    public Tuzdyks() {
        tuzdyks = new int[2];
        reset();
    }

    public int getTuzdyk(int playerColor) {
        validatePlayerColor(playerColor);
        return tuzdyks[playerColor];
    }

    public void setTuzdyk(int playerColor, int holeIndex) {
        validatePlayerColor(playerColor);
        tuzdyks[playerColor] = holeIndex;
    }

    public void reset() {
        tuzdyks[WHITE_TUZDYK] = 0;
        tuzdyks[BLACK_TUZDYK] = 0;
    }
}