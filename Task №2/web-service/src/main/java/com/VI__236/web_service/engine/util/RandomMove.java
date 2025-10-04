package com.VI__236.web_service.engine.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomMove {
    private final int MIN = 0;

    public int randomCoordinate(int boardSize){

        return ThreadLocalRandom.current().nextInt(MIN, boardSize);
    }
}
