package com.VI__236.web_service.services.nextMove;

import com.VI__236.web_service.dto.BoardDto;
import com.VI__236.web_service.dto.SimpleMoveDto;
import org.springframework.stereotype.Component;

@Component
public interface NextMoveService {
    SimpleMoveDto nextMove(BoardDto boardDto);
}
