package org.VI__236.util;

import org.VI__236.models.board.GameBoard;

public class IsValidMove {

    public boolean moveValidator(int coordinateX, int coordinateY, GameBoard gameBoard){

        int [][] checkingBoard = gameBoard.getBoard();

        if (coordinateX < 0 || coordinateX >= checkingBoard.length ||
                coordinateY < 0 || coordinateY >=  checkingBoard[coordinateX].length) {
            return false;
        }
        else{
            return true;
        }
    }
}
