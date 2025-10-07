package com.VI__236.web_service.engine;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class RandomMove {
    private final int MIN = 0;

    public int randomCoordinate(int boardSize){
        Random random = new Random();

        int max = boardSize;
        int randomNumber = random.nextInt(max - MIN) + MIN;

        return randomNumber;
    }
}
