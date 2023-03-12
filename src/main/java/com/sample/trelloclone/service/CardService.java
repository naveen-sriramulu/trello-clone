package com.sample.trelloclone.service;


import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.exception.EmptyCardsException;
import com.sample.trelloclone.mapper.BoardMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
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
        validate(tag);
        List<CardDto> cards = boardMapper
                .toDto(dataService
                        .getBoard()
                        .orElseThrow(EmptyCardsException::new)
                )
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
        validate(column);
        List<CardDto> cards = boardMapper
                .toDto(dataService
                        .getBoard()
                        .orElseThrow(EmptyCardsException::new)
                )
                .getColumns()
                .stream()
                .filter(columnDto -> columnDto.getName().equals(column))
                .findFirst()
                .orElseThrow(EmptyCardsException::new)
                .getCards()
                .stream()
                .toList();

        if (CollectionUtils.isEmpty(cards)) {
            throw new EmptyCardsException();
        }
        return cards;
    }

    public List<CardDto> getCardsCreatedAfter(ZonedDateTime createdAfter) {
        if (createdAfter == null) {
            throw new EmptyCardsException();
        }
        List<CardDto> cards = boardMapper
                .toDto(dataService
                        .getBoard()
                        .orElseThrow(EmptyCardsException::new)
                )
                .getColumns()
                .stream()
                .flatMap(columnDto -> columnDto.getCards().stream())
                .filter(cardDto -> cardDto.getCreatedOn().toEpochSecond() >= createdAfter.toEpochSecond())
                .toList();

        if (CollectionUtils.isEmpty(cards)) {
            throw new EmptyCardsException();
        }
        return cards;
    }

    private void validate(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            throw new EmptyCardsException();
        }
    }
}
