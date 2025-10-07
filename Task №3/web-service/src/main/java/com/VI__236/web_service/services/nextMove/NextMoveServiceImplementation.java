package com.VI__236.web_service.services.nextMove;

import com.VI__236.web_service.dto.BoardDto;
import com.VI__236.web_service.dto.SimpleMoveDto;
import com.VI__236.web_service.engine.IsValidMove;
import com.VI__236.web_service.engine.RandomMove;
import com.VI__236.web_service.models.GameBoard;
import com.VI__236.web_service.util.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NextMoveServiceImplementation implements NextMoveService{

    private SimpleMoveDto simpleMoveDto;
    private GameBoard gameBoard;
    private RandomMove randomMove;
    private IsValidMove isValidMove;
    private DataParser dataParser;

    @Autowired
    public NextMoveServiceImplementation(SimpleMoveDto simpleMoveDto,
                                         GameBoard gameBoard, RandomMove randomMove,
                                         IsValidMove isValidMove, DataParser dataParser){

        this.simpleMoveDto = simpleMoveDto;
        this.gameBoard = gameBoard;
        this.randomMove = randomMove;
        this.isValidMove = isValidMove;
        this.dataParser = dataParser;
    }

    @Override
    public SimpleMoveDto nextMove(BoardDto boardDto) {
        int x;
        int y;

        char [][] tempBoard = dataParser.dataToBoardParser(boardDto.getData(), boardDto.getSize());
        gameBoard.setBoard(tempBoard);


        while (true){
            x = randomMove.randomCoordinate(boardDto.getSize());
            y = randomMove.randomCoordinate(boardDto.getSize());

            if(isValidMove.moveValidator(x, y, gameBoard) == true &&
                    tempBoard[x][y] == ' '){
                break;
            }
        }

        simpleMoveDto.setX(x);
        simpleMoveDto.setY(y);
        simpleMoveDto.setColor(boardDto.getNextPlayerColor());

        return simpleMoveDto;
    }
}
