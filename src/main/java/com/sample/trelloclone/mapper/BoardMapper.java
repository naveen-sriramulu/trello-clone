package com.sample.trelloclone.mapper;

import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.dto.ColumnDto;
import com.sample.trelloclone.entity.Board;
import com.sample.trelloclone.entity.Card;
import com.sample.trelloclone.entity.Column;
import com.sample.trelloclone.entity.Label;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BoardMapper {

    public BoardDto toDto(Board board) {
        return BoardDto
                .builder()
                .name(board.getName())
                .columns(getColumns(board))
                .build();
    }

    private Set<ColumnDto> getColumns(Board board) {
        if (board.getColumns() == null) {
            return Collections.emptySet();
        }
        return board
                .getColumns()
                .stream()
                .map(column -> ColumnDto
                        .builder()
                        .name(column.getName())
                        .cards(getCards(column)
                        )
                        .build()
                )
                .collect(Collectors.toSet());
    }

    private Set<CardDto> getCards(Column column) {
        if (column.getCards() == null) {
            return Collections.emptySet();
        }
        return column
                .getCards()
                .stream()
                .map(card -> CardDto
                        .builder()
                        .title(card.getTitle())
                        .description(card.getDescription())
                        .column(column.getName())
                        .assignedTo(card.getAssignedTo() != null
                                ? card.getAssignedTo().getName() : null
                        )
                        .reportedBy(card.getReportedBy() != null
                                ? card.getReportedBy().getName() : null
                        )
                        .labels(getLabels(card)
                        )
                        .build()
                )
                .collect(Collectors.toSet());
    }

    private Set<String> getLabels(Card card) {
        if (card.getLabels() == null) {
            return Collections.emptySet();
        }
        return card
                .getLabels()
                .stream()
                .map(Label::getTag)
                .collect(Collectors.toSet());
    }

}
