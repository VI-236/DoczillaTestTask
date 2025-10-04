package org.VI__236.models.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.VI__236.models.board.GameBoard;
import org.VI__236.models.player.Color;
import org.VI__236.models.player.Player;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentGameData {

    private Player playerOne;
    private Player playerTwo;
    private GameBoard gameBoard;

    private int numOfTurns;
    private Color whoesTurnNext;
    private boolean freeCells = true;
    private boolean winCombination = false;

    private int boardSize;

    private int lastMoveCoordinateX;
    private int lastMoveCoordinateY;
}
