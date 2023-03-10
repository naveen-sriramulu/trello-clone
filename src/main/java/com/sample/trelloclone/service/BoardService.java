package com.sample.trelloclone.service;


import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.entity.Board;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class BoardService {

    private final MockDataService dataService;

    public BoardService(MockDataService dataService) {
        this.dataService = dataService;
    }

    public Optional<BoardDto> getBoard() {

        //todo
        return null;
    }
}
