package org.VI__236.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomMove {
    private final int MIN = 0;

    public int randomCoordinate(int boardSize){

        return ThreadLocalRandom.current().nextInt(MIN, boardSize);
    }
}
