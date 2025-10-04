package org.VI__236.services;

import org.VI__236.models.game.CurrentGameData;
import org.VI__236.models.player.PlayerType;
import org.VI__236.util.NextPlayerColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleMoveCommand {

    private Movement movement = new Movement();
    private boolean moveIsDone = false;
    private NextPlayerColor nextPlayerColor = new NextPlayerColor();


    public boolean moveCommand (String command, CurrentGameData currentGameData) {

        if (currentGameData.getPlayerOne() == null) {
            System.out.println("Game wasn't started. Enter the \"HELP\" command for detailed instructions.");
            return moveIsDone;
        }

        Pattern pattern = Pattern.compile("MOVE\\s*(\\d+)\\s*,\\s*(\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(command);

        if (!matcher.matches()) {
            System.out.println("Incorrect command. Enter the \"HELP\" command for detailed instructions.");
            return moveIsDone;
        }

        try {
            int coordinateX = Integer.parseInt(matcher.group(1));
            int coordinateY = Integer.parseInt(matcher.group(2));


            if(movement.move (coordinateX, coordinateY, currentGameData.getBoardSize(), currentGameData,
                    currentGameData.getWhoesTurnNext())){

                currentGameData.setWhoesTurnNext(nextPlayerColor.colorChanger(currentGameData.getGameBoard()));
                moveIsDone = true;
            }

            //If the next player is a computer, this part of code will make his turn
            if (currentGameData.getPlayerOne().getPlayerType() == PlayerType.COMP ||
                currentGameData.getPlayerTwo().getPlayerType() == PlayerType.COMP) {

                while (true) {
                    boolean moveIsDoneChecker = movement.move(currentGameData.getBoardSize(), currentGameData);

                    if (moveIsDoneChecker || currentGameData.isFreeCells() ||
                            currentGameData.isWinCombination()) {

                        moveIsDone = true;
                        currentGameData.setWhoesTurnNext(nextPlayerColor.colorChanger(currentGameData.getGameBoard()));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Incorrect command. Enter the \"HELP\" command for detailed instructions.");
        }
        return moveIsDone;
    }
}
