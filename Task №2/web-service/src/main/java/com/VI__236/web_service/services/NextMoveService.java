package com.VI__236.web_service.services;

import com.VI__236.web_service.dto.SimpleMoveDto;
import com.VI__236.web_service.models.BoardModel;
import org.springframework.stereotype.Component;

@Component
public interface NextMoveService {

    SimpleMoveDto nextMove(BoardModel boardModel);
}
