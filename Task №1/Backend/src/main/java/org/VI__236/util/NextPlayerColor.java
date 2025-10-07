package org.VI__236.util;

import org.VI__236.models.board.GameBoard;
import org.VI__236.models.player.Color;

public class NextPlayerColor {

    public Color colorChanger (GameBoard gameBoard){
        char [][] board = gameBoard.getBoard();
        int numOfMoves = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] != '_'){
                    numOfMoves++;
                }
            }
        }

        if(numOfMoves % 2 == 0){
            return Color.WHITE;
        }
        else{
            return Color.BLACK;
        }
    }
}
