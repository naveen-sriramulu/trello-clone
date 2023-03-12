package com.sample.trelloclone.controller;

import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.entity.Card;
import com.sample.trelloclone.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    public void get_board_ok_response() throws Exception {
        // Given
        when(cardService.getCardsByTag("test-tag")).thenReturn(List.of(CardDto.builder()
                .title("Create Readme")
                        .column("In Progress")
                .labels(Set.of("test-tag"))
                .build()));

        // when
        ResultActions response = mockMvc.perform(get("/cards/tag/test-tag"));

        // then
        response.andExpect(status().isOk());
    }

    @Test
    public void get_board_no_content_response() throws Exception {
        // Given
        when(cardService.getCardsByTag("no-tag")).thenReturn(Collections.emptyList());

        // when
        ResultActions response = mockMvc.perform(get("/cards/tag/no-tag"));

        // then
        response.andExpect(status().isNoContent());
    }

}