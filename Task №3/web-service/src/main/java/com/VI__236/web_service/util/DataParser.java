package com.VI__236.web_service.util;

import org.springframework.stereotype.Component;

@Component
public class DataParser {

    public char [][] dataToBoardParser(String data, int size){
        char [][] result = new char[size][size];
        char [] stringToArray = data.toCharArray();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                int index = i * size + j;
                if (index < stringToArray.length){
                    result[i][j] = stringToArray[index];
                }
                else {
                    result[i][j] = ' ';
                }
            }
        }
        return result;
    }
}
