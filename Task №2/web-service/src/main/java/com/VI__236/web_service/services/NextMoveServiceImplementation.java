package com.VI__236.web_service.services;

import com.VI__236.web_service.dto.SimpleMoveDto;
import com.VI__236.web_service.models.BoardModel;
import com.VI__236.web_service.models.SimpleMoveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NextMoveServiceImplementation implements NextMoveService{

    private SimpleMoveDto simpleMoveDto;
    private SimpleMoveModel simpleMoveModel;

    @Autowired
    public NextMoveServiceImplementation(SimpleMoveDto simpleMoveDto, SimpleMoveModel simpleMoveModel){
        this.simpleMoveDto = simpleMoveDto;
        this.simpleMoveModel = simpleMoveModel;

    }
    @Override
    public SimpleMoveDto nextMove(BoardModel boardModel) {
        return null;
    }
}
