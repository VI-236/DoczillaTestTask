package org.VI__236.util;

import org.VI__236.models.board.GameBoard;

public class GameVisualization {

    public void gameVisual(GameBoard gameBoard){
        char [][] board = gameBoard.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%4c", board[i][j]);
            }
            System.out.println();
        }
    }
}
