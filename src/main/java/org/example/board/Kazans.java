package org.example.board;

public class Kazans {
    public static final int WHITE_KAZAN = 0;
    public static final int BLACK_KAZAN = 1;

    public Kazans() {
        kazans = new int[2];
    }

    public int getKazan(int playerColor) {
        validatePlayerColor(playerColor);
        return kazans[playerColor];
    }

    public void addToKazan(int playerColor, int seeds) {
        validatePlayerColor(playerColor);
        kazans[playerColor] += seeds;
    }

    public void reset() {
        kazans[WHITE_KAZAN] = 0;
        kazans[BLACK_KAZAN] = 0;
    }
}