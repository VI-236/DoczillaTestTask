package com.VI__236.web_service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GameStatusDto {
    private int status;
    private String color;
    private String message;
}
