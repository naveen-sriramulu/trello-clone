package com.sample.trelloclone.service;


import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class CardService {

    private final MockDataService dataService;
    private final BoardMapper boardMapper;

    public CardService(MockDataService dataService, BoardMapper boardMapper) {
        this.dataService = dataService;
        this.boardMapper = boardMapper;
    }

    public List<CardDto> getCardsByTag(String tag) {
        return Collections.emptyList();
    }
}
