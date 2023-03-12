package com.sample.trelloclone.service;

import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.entity.Board;
import com.sample.trelloclone.entity.Card;
import com.sample.trelloclone.entity.Column;
import com.sample.trelloclone.exception.EmptyCardsException;
import com.sample.trelloclone.mapper.BoardMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {
    @Mock
    private MockDataService mockDataService;

    @Mock
    private BoardMapper boardMapper;
    @InjectMocks
    private CardService cardService;

    @BeforeEach
    void init() {
        lenient()
                .when(boardMapper.toDto(any()))
                .thenAnswer(i -> new BoardMapper().toDto((Board) i.getArguments()[0]));
    }

    @Test
    public void get_cards_by_tag_matching_found() {
        // Given
        when(mockDataService.getBoard()).thenReturn(new MockDataService().getBoard());

        // When
        List<CardDto> cards = cardService.getCardsByTag("MVP");

        // Then
        assertFalse(CollectionUtils.isEmpty(cards));
        assertEquals(4, cards.size());
        assertTrue(cards
                .stream()
                .allMatch(card -> card.getLabels().contains("MVP")));
        assertTrue(cards
                .stream()
                .map(CardDto::getTitle)
                .allMatch(title -> Set.of("Create Readme", "Basic UI Design", "Design Login Screen", "Add menu item").contains(title))
        );
        assertTrue(cards.stream().allMatch(card -> StringUtils.isNotBlank(card.getColumn())));
    }

    @Test
    public void get_cards_by_tag_empty_cards_exception_when_empty_tag_search() {
        assertThrows(
                // Then
                EmptyCardsException.class,
                // When
                () -> cardService.getCardsByTag("")
        );

        assertThrows(
                // Then
                EmptyCardsException.class,
                // When
                () -> cardService.getCardsByTag(" ")
        );

        assertThrows(
                // Then
                EmptyCardsException.class,
                // When
                () -> cardService.getCardsByTag(null)
        );
    }

    @Test
    public void get_cards_by_tag_empty_cards_exception_when_no_board_configured() {
        // Given
        when(mockDataService.getBoard()).thenReturn(Optional.empty());
        assertThrows(
                // Then
                EmptyCardsException.class,
                // When
                () -> cardService.getCardsByTag("test")
        );
    }

    @Test
    public void get_cards_by_tag_empty_cards_exception_when_cards_has_empty_tags() {
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
                                                .build())
                                        )
                                        .build()
                        )
                )
                .build();
        when(mockDataService.getBoard()).thenReturn(Optional.of(board));

        assertThrows(
                // Then
                EmptyCardsException.class,
                // When
                () -> cardService.getCardsByTag("test")
        );
    }

    @Test
    public void get_cards_by_tag_empty_cards_exception_when_no_matching_tag_found() {
        // Given
        when(mockDataService.getBoard()).thenReturn(new MockDataService().getBoard());
        assertThrows(
                // Then
                EmptyCardsException.class,
                // When
                () -> cardService.getCardsByTag("test")
        );
    }


}
