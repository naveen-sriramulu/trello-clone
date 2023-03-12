package com.sample.trelloclone.service;

import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.dto.ColumnDto;
import com.sample.trelloclone.entity.Board;
import com.sample.trelloclone.entity.Column;
import com.sample.trelloclone.exception.BoardNotFoundException;
import com.sample.trelloclone.mapper.BoardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    private MockDataService mockDataService;

    @Mock
    private BoardMapper boardMapper;
    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void init() {
        lenient()
                .when(boardMapper.toDto(any()))
                .thenAnswer(i -> new BoardMapper().toDto((Board) i.getArguments()[0]));
    }

    @Test
    public void get_board_when_board_is_configured() {
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
        when(mockDataService.getBoard()).thenReturn(Optional.of(board));

        // When
        BoardDto boardDto = boardService.getBoard().get();

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
    public void board_not_found_exception_when_no_board_configured() {
        // Given
        when(mockDataService.getBoard()).thenReturn(Optional.empty());
        assertThrows(
                // Then
                BoardNotFoundException.class,
                // When
                () -> boardService.getBoard()
        );


        // Given
        when(mockDataService.getBoard()).thenReturn(Optional.empty());
        assertThrows(
                // Then
                BoardNotFoundException.class,
                // When
                () -> boardService.getBoard()
        );

    }


}
