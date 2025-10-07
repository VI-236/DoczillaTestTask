package org.VI__236.util;

import org.VI__236.models.board.GameBoard;

public class IsBoardFull {

    public boolean isBoardFull(GameBoard gameBoard, int boardSize) {
        int totalCount = 0;
        char[][] board = gameBoard.getBoard();

        if (board == null) {
            return false;
        }

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != '_') {
                        totalCount++;
                    }
                }
            }

            if (totalCount == boardSize * boardSize) {
                return false;
            }
            else {
                return true;
        }
    }
}
