package com.VI__236.web_service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SimpleMoveDto {

    private int x;
    private int y;
    private String color;
}
