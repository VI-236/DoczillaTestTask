package com.VI__236.web_service.controllers;

import com.VI__236.web_service.dto.BoardDto;
import com.VI__236.web_service.dto.GameStatusDto;
import com.VI__236.web_service.dto.SimpleMoveDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SquareGameController {

    @PostMapping("/{rules}/nextMove")
    public SimpleMoveDto nextMove(@RequestParam String rules, @RequestBody BoardDto boardDto){
        return studentMapper. studentModelToDto(studentService.createStudent(studentMapper.studentDtoToModel(studentDto)));
    }

    @PostMapping("/{rules}/gameStatus")
    public GameStatusDto gameStatus(@RequestParam String rules, @RequestBody BoardDto boardDto){
        return studentMapper. studentModelToDto(studentService.createStudent(studentMapper.studentDtoToModel(studentDto)));
    }
}
