package org.VI__236.services;

import org.VI__236.util.GameVisualization;
import org.VI__236.models.board.GameBoard;
import org.VI__236.models.game.CurrentGameData;
import org.VI__236.models.player.Color;
import org.VI__236.models.player.Player;
import org.VI__236.models.player.PlayerType;
import org.VI__236.util.NextPlayerColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleGameCommand {

    private CurrentGameData currentGameData = new CurrentGameData();
    private GameBoard gameBoard = new GameBoard();
    private Movement movement = new Movement();
    private GameVisualization gameVisualization = new GameVisualization();
    private NextPlayerColor nextPlayerColor = new NextPlayerColor();

    public CurrentGameData gameCommand(String command) {
        Pattern pattern = Pattern.compile("GAME\\s+(\\d+)\\s*,\\s*(\\w+\\s+[WB])\\s*,\\s*(\\w+\\s+[WB])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(command);

        if (!matcher.matches()) {
            System.out.println("Incorrect command. Enter the \"HELP\" command for detailed instructions.");
            return currentGameData;
        }

        //Game field created and saved in CurrentGameData class
        try {
            int size = Integer.parseInt(matcher.group(1));
            System.out.println(size);
            if (size <= 2) {
                System.out.println("Incorrect size of game board. Repeat command please.");
                return currentGameData;
            }
            else{
                int [][] boardSize = new int[size][size];
                gameBoard.setBoard(boardSize);

                currentGameData.setGameBoard(gameBoard);
                currentGameData.setBoardSize(size);
            }


            Player p1 = (parsePlayersParams(matcher.group(2)));
            Player p2 = (parsePlayersParams(matcher.group(3)));

            if (p1 == null ||p1 == null || p1.getColor() == p2.getColor()) {
                System.out.println("Incorrect player/players parameters. Repeat command please.");
                return null;
            }
            else{
                currentGameData.setPlayerOne(p1);
                currentGameData.setPlayerTwo(p2);
            }

            currentGameData.setWhoesTurnNext(Color.WHITE);
            currentGameData.setFreeCells(true);
            currentGameData.setWinCombination(false);
            currentGameData.setNumOfTurns(1);

            System.out.println("New game started");

            System.out.println("First player parameters: " + currentGameData.getPlayerOne().getPlayerType() + " " + currentGameData.getPlayerOne().getColor());
            System.out.println("Second player parameters: " + currentGameData.getPlayerTwo().getPlayerType() + " " + currentGameData.getPlayerTwo().getColor());

            gameVisualization.gameVisual(currentGameData.getGameBoard());

            // If first player is computer, make move
            if (currentGameData.getPlayerOne().getPlayerType() == PlayerType.COMP ||
                    currentGameData.getPlayerTwo().getPlayerType() == PlayerType.COMP) {

                while (true){
                    boolean moveIsDoneChecker = movement.move(currentGameData.getBoardSize(), currentGameData);

                    if (moveIsDoneChecker == true){

                        currentGameData.setWhoesTurnNext(nextPlayerColor.colorChanger(currentGameData.getGameBoard()));
                        break;
                    }
                }
            }

            //If all players are computer.
            if(currentGameData.getPlayerOne().getPlayerType() == PlayerType.COMP &&
                    currentGameData.getPlayerTwo().getPlayerType() == PlayerType.COMP){

                while(true){

                    movement.move(currentGameData.getBoardSize(), currentGameData);
                    currentGameData.setWhoesTurnNext(nextPlayerColor.colorChanger(currentGameData.getGameBoard()));

                        if(currentGameData.isWinCombination() == true){
                            break;
                        }
                        if(currentGameData.isFreeCells() == false){
                            break;
                        }
                    }
                }

        } catch (Exception e) {
            System.out.println("Incorrect command. HandleGameCommand.114");
        }

        return currentGameData;
    }

    private Player parsePlayersParams(String playerStr) {
        Pattern pattern = Pattern.compile("(USER|COMP)\\s+([WB])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(playerStr.trim());

        if (!matcher.matches()) {
            return null;
        }

        PlayerType type = matcher.group(1).equalsIgnoreCase("USER") ? PlayerType.USER : PlayerType.COMP;
        Color color = matcher.group(2).equalsIgnoreCase("W") ? Color.WHITE : Color.BLACK;

        return new Player(color, type);
    }
}