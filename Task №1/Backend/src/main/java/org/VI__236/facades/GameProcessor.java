package org.VI__236.facades;

import org.VI__236.models.game.CurrentGameData;
import org.VI__236.models.player.Color;
import org.VI__236.services.HandleGameCommand;
import org.VI__236.services.HandleMoveCommand;

import java.util.Scanner;

public class GameProcessor {

    private CurrentGameData currentGameData = new CurrentGameData();
    private HandleGameCommand handleGameCommand = new HandleGameCommand();
    private HandleMoveCommand handleMoveCommand = new HandleMoveCommand();

    private static final Scanner scanner = new Scanner(System.in);

    public void gameStart(){
        System.out.println("Welcome to Square Game!");

        while (true) {

            String upperCommand = commandGet().toUpperCase();
            String [] commandList = upperCommand.split(" ");

            switch(commandList[0]){

                case "GAME":
                    currentGameData = handleGameCommand.gameCommand(upperCommand);
                    break;

                case "MOVE":
                    boolean moveIsDone = handleMoveCommand.moveCommand(upperCommand, currentGameData);

                    if(moveIsDone == false){
                        System.out.println("Incorrect movement command, please try again.");
                    }
                    break;

                case "EXIT":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                case "HELP":
                    System.out.println(
                            "Available commands:\n" +
                                    "GAME N, U1, U2 - start new game\n" +
                                    "  N - board size (integer > 2)\n" +
                                    "  U1, U2 - players in format: TYPE C\n" +
                                    "    TYPE: 'user' or 'comp'\n" +
                                    "    C: 'W' (white) or 'B' (black)\n" +
                                    "MOVE X, Y - make a move\n" +
                                    "EXIT - exit program\n" +
                                    "HELP - show this help\n" +
                                    "\n" +
                                    "Game command example: game 5, user w, comp b \n" +
                                    "Board size is 5x5, first player is a human and plays with white chips, \n" +
                                    "second player is a computer and plays with black chips.\n" +
                                    "\n" +
                                    "Move command example: move 1, 2 \n" +
                                    "Player places his chip on the square at the address of \n" +
                                    "the 5th row and the 4th column.");
                    break;

                default:
                    System.out.println("Incorrect game star command. Enter the \"HELP\" command for detailed instructions.");
                    break;
            }

            if (currentGameData.isWinCombination() == true &&
                    currentGameData.getWhoesTurnNext() == Color.WHITE){
                System.out.println("Game over! Black is winner!");

            }
            else if(currentGameData.isWinCombination() == true &&
                    currentGameData.getWhoesTurnNext() == Color.BLACK){
                System.out.println("Game over! White is winner!");

            }
            else if(currentGameData.isFreeCells() == false){
                System.out.println("Game over! There are no free cells left.");

            }
        }
    }

    private String commandGet(){
        String command;

        command = scanner.nextLine().trim();
        return command;
    }
}
