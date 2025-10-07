package com.VI__236.web_service.services.gameStatus;

import com.VI__236.web_service.dto.BoardDto;
import com.VI__236.web_service.dto.GameStatusDto;
import org.springframework.stereotype.Component;

@Component
public interface GameStatusService {
    GameStatusDto gameStatus(BoardDto boardDto);
}
