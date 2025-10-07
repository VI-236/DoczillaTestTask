package com.VI__236.web_service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BoardDto {
    private int size;
    private String data;
    private String nextPlayerColor;
}
