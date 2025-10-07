package com.VI__236.web_service.controllers;

import com.VI__236.web_service.dto.BoardDto;
import com.VI__236.web_service.dto.GameStatusDto;
import com.VI__236.web_service.dto.SimpleMoveDto;
import com.VI__236.web_service.services.gameStatus.GameStatusService;
import com.VI__236.web_service.services.nextMove.NextMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SquareGameController {

    private NextMoveService nextMoveService;
    private GameStatusService gameStatusService;

    @Autowired
    public SquareGameController(NextMoveService nextMoveService,
                                GameStatusService gameStatusService){
        this.nextMoveService = nextMoveService;
        this.gameStatusService = gameStatusService;
    }

    @PostMapping("/{rules}/nextMove")
    public SimpleMoveDto nextMove(@PathVariable String rules, @RequestBody BoardDto boardDto){
        return nextMoveService.nextMove(boardDto);
    }

    @PostMapping("/{rules}/gameStatus")
    public GameStatusDto gameStatus(@PathVariable String rules, @RequestBody BoardDto boardDto){
        return gameStatusService.gameStatus(boardDto);
    }
}
