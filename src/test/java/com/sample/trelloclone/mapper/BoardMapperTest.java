package com.sample.trelloclone.mapper;

import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.dto.ColumnDto;
import com.sample.trelloclone.entity.Board;
import com.sample.trelloclone.entity.Card;
import com.sample.trelloclone.entity.Column;
import com.sample.trelloclone.entity.Label;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class BoardMapperTest {

    private BoardMapper boardMapper = new BoardMapper();

    @Test
    public void get_board_when_board_is_configured_with_no_columns() {
        // Given
        Board board = Board.builder().name("test-board").build();

        // When
        BoardDto boardDto = boardMapper.toDto(board);

        // Then
        assertEquals("test-board", boardDto.getName());
        assertTrue(boardDto.getColumns().isEmpty());
    }

    @Test
    public void get_board_when_board_is_configured_with_columns_and_no_cards() {
        // Given
        Board board = Board.builder()
                .id(1)
                .name("test-board")
                .columns(Set.of(
                                Column.builder().id(1).name("in-progress").build(),
                                Column.builder().id(1).name("dev").build(),
                                Column.builder().id(1).name("done").build()
                        )
                )
                .build();

        // When
        BoardDto boardDto = boardMapper.toDto(board);

        // Then
        assertEquals("test-board", boardDto.getName());
        assertEquals(3, boardDto.getColumns().size());
        assertTrue(boardDto
                .getColumns()
                .stream()
                .map(ColumnDto::getName)
                .allMatch(name -> Set.of("in-progress", "dev", "done").contains(name))
        );
    }

    @Test
    public void get_board_when_board_is_configured_with_columns_and_cards() {
        // Given
        Board board = Board.builder()
                .id(1)
                .name("test-board")
                .columns(Set.of(
                                Column.builder()
                                        .id(1)
                                        .name("in-progress")
                                        .cards(Set.of(Card.builder()
                                                .id(1)
                                                .title("Card-1")
                                                .labels(Set.of(Label.builder().tag("l1").build()))
                                                .build())
                                        )
                                        .build(),
                                Column.builder()
                                        .id(1)
                                        .name("dev")
                                        .cards(Set.of(Card.builder()
                                                        .id(1)
                                                        .title("Card-2")
                                                        .labels(Set.of(Label.builder().tag("l1").build(), Label.builder().tag("l2").build()))
                                                        .build(),
                                                Card.builder()
                                                        .id(1)
                                                        .title("Card-3")
                                                        .build()
                                        ))
                                        .build(),
                                Column.builder().id(1).name("done").build()
                        )
                )
                .build();

        // When
        BoardDto boardDto = boardMapper.toDto(board);

        // Then
        assertEquals("test-board", boardDto.getName());
        assertEquals(3, boardDto.getColumns().size());
        assertTrue(boardDto
                .getColumns()
                .stream()
                .allMatch(columnDto -> Set.of("in-progress", "dev", "done").contains(columnDto.getName()))
        );
        ColumnDto inProgress = boardDto
                .getColumns()
                .stream()
                .filter(columnDto -> columnDto.getName().equals("in-progress"))
                .findAny()
                .get();
        assertEquals(1, inProgress.getCards().size());
        assertTrue(inProgress
                .getCards()
                .stream()
                .allMatch(cardDto -> Objects.equals("Card-1", cardDto.getTitle()))
        );
        assertEquals(1, inProgress
                .getCards()
                .stream()
                .findAny()
                .get()
                .getLabels().size());
        assertTrue(inProgress
                .getCards()
                .stream()
                .findAny()
                .get()
                .getLabels()
                .contains("l1"));
    }
}
