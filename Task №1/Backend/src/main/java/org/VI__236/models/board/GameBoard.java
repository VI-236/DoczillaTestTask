package org.VI__236.models.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class GameBoard {

    @Setter
    @Getter
    private char [][] board;
}
