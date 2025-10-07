package org.VI__236.services;

import org.VI__236.util.*;
import org.VI__236.models.board.GameBoard;
import org.VI__236.models.game.CurrentGameData;
import org.VI__236.models.player.Color;

import java.util.concurrent.ThreadLocalRandom;

public class Movement {

    private IsValidMove isValidMove = new IsValidMove();
    private IsBoardFull isBoardFull = new IsBoardFull();
    private GameBoard gameBoard = new GameBoard();
    private WinChecker winChecker = new WinChecker();
    private boolean moveIsDone = true;
    private GameVisualization gameVisualization = new GameVisualization();
    private NextPlayerColor nextPlayerColor = new NextPlayerColor();
    private RandomMove randomMove = new RandomMove();

    public boolean move(int coordinateX, int coordinateY, int boardSize, CurrentGameData currentGameData, Color color){

        char [][] temporaryBoard = currentGameData.getGameBoard().getBoard();

        if(isValidMove.moveValidator(coordinateX, coordinateY, currentGameData.getGameBoard())||
                !isBoardFull.isBoardFull(currentGameData.getGameBoard(), boardSize)) {
            if (color.equals(Color.WHITE)){
                temporaryBoard[coordinateX][coordinateY] = 'w';

                gameBoard.setBoard(temporaryBoard);
                currentGameData.setGameBoard(gameBoard);

                System.out.println("White move.");
            }
            else if (color.equals(Color.BLACK)){
                temporaryBoard[coordinateX][coordinateY] = 'b';
                System.out.println("Black move.");
            }
            moveIsDone = true;
        }
        else {
            System.out.println("Invalid movement.");
            moveIsDone = false;
        }

        if(moveIsDone == true){

            currentGameData.setFreeCells(isBoardFull.isBoardFull(currentGameData.getGameBoard(),
                                                                 currentGameData.getBoardSize()));

            currentGameData.setNumOfTurns(currentGameData.getNumOfTurns() + 1);

            System.out.println("Number of move: " + currentGameData.getNumOfTurns());

        }
        currentGameData.setWinCombination(winChecker.winChek(coordinateX,
                coordinateY,
                currentGameData.getGameBoard()));

        gameVisualization.gameVisual(currentGameData.getGameBoard());
        return moveIsDone;
    }

    //Overload method for computer players.
    public boolean move(int boardSize, CurrentGameData currentGameData){

        gameBoard = currentGameData.getGameBoard();
        int coordinateX;
        int coordinateY;

        //The stream falls asleep for 1.5 seconds to make it easier for a human to follow the game.
        //But because the computer makes random moves and filters out those that fall on already occupied squares,
        //sometimes it may seem that the game has hang, but this is not true.
        try{
            Thread.sleep(1500);
        }
        catch (InterruptedException e){
        }

        while(true){

            //Generating random coordinates for comp moves.
            coordinateX = randomMove.randomCoordinate(boardSize);
            coordinateY = randomMove.randomCoordinate(boardSize);

            if (currentGameData.isFreeCells() == false){
                moveIsDone = true;
                break;
            }

            char [][] temporaryBoard = currentGameData.getGameBoard().getBoard();

            if (temporaryBoard[coordinateX][coordinateY] != '_' || currentGameData.isFreeCells() == false){
                return moveIsDone = false;
            }
            else{
                if(isValidMove.moveValidator(coordinateX, coordinateY, gameBoard) ||
                        !isBoardFull.isBoardFull(currentGameData.getGameBoard(), boardSize)) {
                    temporaryBoard = gameBoard.getBoard();

                    if(currentGameData.getWhoesTurnNext() == Color.WHITE){
                        temporaryBoard[coordinateX][coordinateY] = 'w';

                        gameBoard.setBoard(temporaryBoard);
                        currentGameData.setGameBoard(gameBoard);

                        System.out.println("White move.");
                        System.out.println("Number of move: " + currentGameData.getNumOfTurns());
                    }
                    else{
                        temporaryBoard[coordinateX][coordinateY] = 'b';
                        gameBoard.setBoard(temporaryBoard);
                        currentGameData.setGameBoard(gameBoard);

                        System.out.println("Black move.");
                        System.out.println("Number of move: " + currentGameData.getNumOfTurns());
                    }
                    moveIsDone = true;
                    currentGameData.setFreeCells(isBoardFull.isBoardFull(currentGameData.getGameBoard(),
                            currentGameData.getBoardSize()));
                    break;
                }

                else{
                    currentGameData.setFreeCells(isBoardFull.isBoardFull(currentGameData.getGameBoard(),
                            currentGameData.getBoardSize()));
                    moveIsDone = false;
                    break;
                }
            }
        }

        if(moveIsDone == true){
            currentGameData.setNumOfTurns(currentGameData.getNumOfTurns() + 1);
            currentGameData.setWhoesTurnNext(nextPlayerColor.colorChanger(currentGameData.getGameBoard()));
        }

        currentGameData.setWinCombination(winChecker.winChek(coordinateX, coordinateY, currentGameData.getGameBoard()));

        currentGameData.setFreeCells(isBoardFull.isBoardFull(currentGameData.getGameBoard(),
                currentGameData.getBoardSize()));

        gameVisualization.gameVisual(currentGameData.getGameBoard());
        return moveIsDone;
    }
}
