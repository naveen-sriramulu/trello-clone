package com.sample.trelloclone.controller;

import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    public void get_board_ok_response() throws Exception {
        // Given
        when(boardService.getBoard()).thenReturn(Optional.of(BoardDto.builder().name("test-board").build()));

        // when
        ResultActions response = mockMvc.perform(get("/board"));

        // then
        response.andExpect(status().isOk());
    }

    @Test
    public void get_board_no_content_response() throws Exception {
        // Given
        // No board configured

        // when
        ResultActions response = mockMvc.perform(get("/board"));

        // then
        response.andExpect(status().isNoContent());
    }
}