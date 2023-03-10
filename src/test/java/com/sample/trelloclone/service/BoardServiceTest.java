package com.sample.trelloclone.service;

import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.dto.ColumnDto;
import com.sample.trelloclone.entity.Board;
import com.sample.trelloclone.entity.Card;
import com.sample.trelloclone.entity.Column;
import com.sample.trelloclone.entity.Label;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    private MockDataService mockDataService;

    @InjectMocks
    private BoardService boardService;

    @Test
    public void get_board_when_no_board_configured() {
        // Given
        when(mockDataService.getBoard()).thenReturn(null);
        // When
        Optional<BoardDto> boardDto = boardService.getBoard();
        // Then
        assertTrue(boardDto.isEmpty());

        // Given
        when(mockDataService.getBoard()).thenReturn(Optional.empty());
        // When
        boardDto = boardService.getBoard();
        // Then
        assertTrue(boardDto.isEmpty());

    }

    @Test
    public void get_board_when_board_is_configured_with_no_columns() {
        // Given
        when(mockDataService.getBoard()).thenReturn(Optional.of(Board.builder().name("test-board").build()));

        // When
        BoardDto boardDto = boardService.getBoard().get();

        // Then
        assertTrue(boardDto.getName().equals("test-board"));
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
        when(mockDataService.getBoard()).thenReturn(Optional.of(Board.builder().name("test-board").build()));

        // When
        BoardDto boardDto = boardService.getBoard().get();

        // Then
        assertEquals(boardDto.getName(), "test-board");
        assertEquals(boardDto.getColumns().size(), 3);
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
        when(mockDataService.getBoard()).thenReturn(Optional.of(Board.builder().name("test-board").build()));

        // When
        BoardDto boardDto = boardService.getBoard().get();

        // Then
        assertEquals(boardDto.getName(), "test-board");
        assertEquals(boardDto.getColumns().size(), 3);
        assertTrue(boardDto
                .getColumns()
                .stream()
                .allMatch(columnDto -> Set.of("in-progress", "dev", "done").contains(columnDto.getName()))
        );
        ColumnDto inProgress = boardDto
                .getColumns()
                .stream()
                .filter(columnDto -> columnDto.getName().equals("in progress"))
                .findAny()
                .get();
        assertEquals(inProgress.getCards().size(), 1);
        assertTrue(inProgress
                .getCards()
                .stream()
                .allMatch(cardDto -> Objects.equals("Card-1", cardDto.getTitle()))
        );
        assertEquals(inProgress
                .getCards()
                .stream()
                .findAny()
                .get()
                .getLabels().size(), 1);
        assertTrue(inProgress
                .getCards()
                .stream()
                .findAny()
                .get()
                .getLabels()
                .contains("l1"));
    }
}
