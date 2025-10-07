package com.VI__236.web_service.services.gameStatus;

import com.VI__236.web_service.util.GameVisualization;
import com.VI__236.web_service.dto.BoardDto;
import com.VI__236.web_service.dto.GameStatusDto;
import com.VI__236.web_service.engine.IsBoardFull;
import com.VI__236.web_service.engine.NextPlayerColor;
import com.VI__236.web_service.models.Color;
import com.VI__236.web_service.models.GameBoard;
import com.VI__236.web_service.engine.WinChecker;
import com.VI__236.web_service.util.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStatusServiceImplementation implements GameStatusService {

    private GameStatusDto gameStatusDto;
    private GameBoard gameBoard;
    private DataParser dataParser;
    private WinChecker winChecker;
    private IsBoardFull isBoardFull;
    private NextPlayerColor nextPlayerColor;

    private GameVisualization gameVisualization;

    @Autowired
    public GameStatusServiceImplementation(GameStatusDto gameStatusDto,
                                           GameBoard gameBoard,
                                           DataParser dataParser,
                                           WinChecker winChecker,
                                           IsBoardFull isBoardFull,
                                           NextPlayerColor nextPlayerColor,

                                           GameVisualization gameVisualization){

        this.gameStatusDto = gameStatusDto;
        this.gameBoard = gameBoard;
        this.dataParser = dataParser;
        this.winChecker = winChecker;
        this.isBoardFull = isBoardFull;
        this.nextPlayerColor = nextPlayerColor;

        this.gameVisualization = gameVisualization;
    }

    @Override
    public GameStatusDto gameStatus(BoardDto boardDto) {
        int status = 0;
        boolean win = false;
        String message;
        String colorForMessage = "none";



        System.out.println("На вход я получил BoardDto: ");
        System.out.println("Size " + boardDto.getSize());
        System.out.println("Data " + boardDto.getData());
        System.out.println("NextPlayer " + boardDto.getNextPlayerColor());

        String color = boardDto.getNextPlayerColor();

        char [][] tempBoard = dataParser.dataToBoardParser(boardDto.getData(), boardDto.getSize());
        gameBoard.setBoard(tempBoard);


        gameVisualization.gameVisual(gameBoard);
        System.out.println("________________________________");

        outerLoop:
        for (int i = 0; i < boardDto.getSize(); i++) {
            for (int j = 0; j < boardDto.getSize(); j++) {
                if (tempBoard[i][j] != ' ') {
                    win = winChecker.winChek(i, j, gameBoard);

                    if(win == true){
                        break outerLoop;
                    }
                }
            }
            gameVisualization.gameVisual(gameBoard);
        }


        if(win == true){

            status = 1;

            if(nextPlayerColor.colorChanger(gameBoard) == Color.BLACK){
                color = "w";
                colorForMessage = "White";
            }
            else {
                color = "b";
                colorForMessage = "Black";
            }
        }

        if(isBoardFull.isBoardFull(gameBoard, boardDto.getSize()) == false && win == false){
            status = 2;
        }

        switch(status){
            case -1:
                message = "Unknown error.";
            break;

            case 0:
                message = "Game in process, there are available cells.";
            break;

            case 1:
                message = "There is a winer! " + colorForMessage + ". Thanks for the game.";
            break;

            case 2:
                message = "It's a draw! There are no available cells left. Thanks for the game.";
            break;

            default:
                message = "Something went wrong...";
            break;
        }

        gameStatusDto.setStatus(status);
        gameStatusDto.setColor(color);
        gameStatusDto.setMessage(message);

        return gameStatusDto;
    }
}
