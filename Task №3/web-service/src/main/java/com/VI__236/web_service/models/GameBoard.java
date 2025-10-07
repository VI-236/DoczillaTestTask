package com.VI__236.web_service.models;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@Setter
@Getter
public class GameBoard {

    private char [][] board;
}
