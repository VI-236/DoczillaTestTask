package com.VI__236.web_service.util;

import com.VI__236.web_service.models.GameBoard;
import org.springframework.stereotype.Component;

@Component
public class GameVisualization {

    public void gameVisual(GameBoard gameBoard){
        char [][] board = gameBoard.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if(board[i][j] == ' '){
                    System.out.print('_');
                }
                if (board[i][j] == 'w'){
                    System.out.print('w');
                }
                if(board[i][j] == 'b'){
                    System.out.print('b');
                };
            }
            System.out.println();
        }
    }
}
