package com.VI__236.web_service.engine;

import com.VI__236.web_service.models.GameBoard;
import com.VI__236.web_service.models.Color;
import org.springframework.stereotype.Component;

@Component
public class NextPlayerColor {

    public Color colorChanger (GameBoard gameBoard){
        char [][] board = gameBoard.getBoard();
        int numOfMoves = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] != ' '){
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
