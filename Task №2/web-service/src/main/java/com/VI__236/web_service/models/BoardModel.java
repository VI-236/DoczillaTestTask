package com.VI__236.web_service.models;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BoardModel {

    private int[][] board;
    private int size;
    private String nextPlayerColor;
}
