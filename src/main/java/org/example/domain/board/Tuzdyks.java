package org.example.domain.board;

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
    private void validatePlayerColor(int playerColor) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
    }
}