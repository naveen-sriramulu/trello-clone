package com.sample.trelloclone.service;


import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.entity.Board;
import com.sample.trelloclone.exception.EmptyCardsException;
import com.sample.trelloclone.mapper.BoardMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service

public class CardService {

    private final MockDataService dataService;
    private final BoardMapper boardMapper;

    public CardService(MockDataService dataService, BoardMapper boardMapper) {
        this.dataService = dataService;
        this.boardMapper = boardMapper;
    }

    public List<CardDto> getCardsByTag(String tag) {
        if (StringUtils.isBlank(tag)) {
            throw new EmptyCardsException();
        }
        Optional<Board> board = dataService
                .getBoard();
        if (board.isEmpty()) {
            throw new EmptyCardsException();
        }
        List<CardDto> cards = boardMapper.toDto(board.get())
                .getColumns()
                .stream()
                .flatMap(columnDto -> columnDto.getCards().stream())
                .filter(cardDto -> cardDto.getLabels().contains(tag))
                .toList();

        if (CollectionUtils.isEmpty(cards)) {
            throw new EmptyCardsException();
        }
        return cards;
    }

    public List<CardDto> getCardsByColumn(String column) {
        return null;
    }
}
