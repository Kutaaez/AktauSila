package org.example.domain.rules;

import org.example.board.BoardState;

public interface IRules {
    boolean canFormTuzdyk(int index, int playerColor, BoardState boardState);
    void applyTuzdyk(int index, int playerColor, BoardState boardState);
    boolean canCapture(int index, int playerColor, BoardState boardState);
    void applyCapture(int index, int playerColor, BoardState boardState);
}