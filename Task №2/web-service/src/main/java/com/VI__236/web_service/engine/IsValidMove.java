package com.VI__236.web_service.engine;

import com.VI__236.web_service.models.GameBoard;
import org.springframework.stereotype.Component;

@Component
public class IsValidMove {

    public boolean moveValidator(int coordinateX, int coordinateY, GameBoard gameBoard){

        char [][] checkingBoard = gameBoard.getBoard();

        if (coordinateX < 0 || coordinateX >= checkingBoard.length ||
                coordinateY < 0 || coordinateY >=  checkingBoard[coordinateX].length) {
            return false;
        }
        else{
            return true;
        }
    }
}
