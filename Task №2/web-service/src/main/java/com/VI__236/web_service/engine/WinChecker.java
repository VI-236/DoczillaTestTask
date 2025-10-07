package com.VI__236.web_service.engine;

import com.VI__236.web_service.models.GameBoard;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WinChecker { ;
    private IsValidMove moveChecker = new IsValidMove();

    public boolean winChek(int coordinateX, int coordinateY, GameBoard gameBoard){

        boolean win = false;

        System.out.println("Enter coordinate Х: " + coordinateX);
        System.out.println("Enter coordinate Y: " + coordinateY);
        char [][] temporaryBoard = gameBoard.getBoard();

        char colorOfCell = temporaryBoard[coordinateX][coordinateY];

        //Small square checker:
        //_ _ _ _ _
        //_ _ o o _
        //_ _ o o _
        //_ _ _ _ _
        try{
            if (moveChecker.moveValidator(coordinateX, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 1, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 1, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY] == colorOfCell) {
                System.out.println("The small square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX + 1, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 1, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY - 1] == colorOfCell) {
                System.out.println("The small square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX - 1 ][coordinateY] == colorOfCell) {
                System.out.println("The small square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX - 1, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY + 1] == colorOfCell) {
                System.out.println("The small square is found.");
                return win = true;
            }

            //Big square checker:
            //_ _ _ _ _
            //_ o _ o _
            //_ _ _ _ _
            //_ o _ o _
            //_ _ _ _ _
            if (moveChecker.moveValidator(coordinateX, coordinateY  + 2, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY  + 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 2, coordinateY + 2, gameBoard) &&
                    temporaryBoard[coordinateX + 2][coordinateY + 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX  + 2, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX  + 2][coordinateY] == colorOfCell) {
                System.out.println("The big square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX  + 2, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX + 2][coordinateY] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 2, coordinateY - 2, gameBoard) &&
                    temporaryBoard[coordinateX + 2][coordinateY - 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX, coordinateY  - 2, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY  - 2] == colorOfCell) {
                System.out.println("The big square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX, coordinateY - 2, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY- 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 2, coordinateY - 2, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY - 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 2, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY] == colorOfCell) {
                System.out.println("The big square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX  - 2, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 2, coordinateY + 2, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY + 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX, coordinateY  + 2, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY  + 2] == colorOfCell) {
                System.out.println("The big square is found.");
                return win = true;
            }

            //Rhombus checker:
            //_ _ o _ _
            //_ o _ o _
            //_ _ o _ _
            if (moveChecker.moveValidator(coordinateX + 1, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 2, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX + 2][coordinateY] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 1, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY - 1] == colorOfCell) {
                System.out.println("The rhombus is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX + 1, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX, coordinateY  - 2, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY - 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY - 1] == colorOfCell) {
                System.out.println("The rhombus is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX - 1, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX  - 2, coordinateY, gameBoard) &&
                    temporaryBoard[coordinateX  - 2][coordinateY] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY + 1] == colorOfCell) {
                System.out.println("The rhombus is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX - 1, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX, coordinateY + 2, gameBoard) &&
                    temporaryBoard[coordinateX][coordinateY + 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 1, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY + 1] == colorOfCell) {
                System.out.println("The rhombus is found.");
                return win = true;
            }

            //Tilted square checker:
            //_ _ _ o _ _
            //_ o _ _ _ _
            //_ _ _ _ o _
            //_ _ o _ _ _
            if (moveChecker.moveValidator(coordinateX - 1, coordinateY + 2, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY + 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 1, coordinateY + 3, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY + 3] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 2, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX + 2][coordinateY + 1] == colorOfCell) {
                System.out.println("The tilted square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX + 2, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX + 2][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 3, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX + 3][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 1, coordinateY - 2, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY - 2] == colorOfCell) {
                System.out.println("The tilted square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX + 1, coordinateY - 2, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY - 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY - 3, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY - 3] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 2, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY - 1] == colorOfCell) {
                System.out.println("The tilted square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX - 2, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 3, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX - 3][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY + 2, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY + 2] == colorOfCell) {

                System.out.println("The tilted square is found.");
                return win = true;
            }

            //Reverse tilted square checker:
            //_ _ o _ _ _
            //_ _ _ _ o _
            //_ o _ _ _ _
            //_ _ _ o _ _
            if (moveChecker.moveValidator(coordinateX + 2, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX + 2][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 1, coordinateY - 3, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY - 3] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY - 2, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY - 2] == colorOfCell) {
                System.out.println("The reverse tilted square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX - 1, coordinateY - 2, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY - 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 3, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX - 3][coordinateY - 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 2, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY + 1] == colorOfCell) {
                System.out.println("The reverse tilted square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX - 2, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 1, coordinateY + 3, gameBoard) &&
                    temporaryBoard[coordinateX - 1][coordinateY + 3] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX - 2, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX - 2][coordinateY - 1] == colorOfCell) {
                System.out.println("The reverse tilted square is found.");
                return win = true;
            }

            if (moveChecker.moveValidator(coordinateX + 1, coordinateY + 2, gameBoard) &&
                    temporaryBoard[coordinateX + 1][coordinateY + 2] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 3, coordinateY + 1, gameBoard) &&
                    temporaryBoard[coordinateX + 3][coordinateY + 1] == colorOfCell &&

                    moveChecker.moveValidator(coordinateX + 2, coordinateY - 1, gameBoard) &&
                    temporaryBoard[coordinateX + 2][coordinateY - 1] == colorOfCell) {
                System.out.println("The reverse tilted square is found.");
                return win = true;
            }
        }
        catch (Exception e){
        }

        return win;
    }
}