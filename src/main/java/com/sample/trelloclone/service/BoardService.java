package com.sample.trelloclone.service;


import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class BoardService {

    private final MockDataService dataService;
    private final BoardMapper boardMapper;

    public BoardService(MockDataService dataService, BoardMapper boardMapper) {
        this.dataService = dataService;
        this.boardMapper = boardMapper;
    }

    public Optional<BoardDto> getBoard() {
        return dataService
                .getBoard()
                .map(boardMapper::toDto);

    }
}
