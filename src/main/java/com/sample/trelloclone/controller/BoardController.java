package com.sample.trelloclone.controller;

import com.sample.trelloclone.dto.BoardDto;
import com.sample.trelloclone.exception.BoardNotFoundException;
import com.sample.trelloclone.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    @Operation(summary = "Get a board")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved the board successfully",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BoardDto.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)
    })
    public ResponseEntity<BoardDto> get() {
        return ResponseEntity.ok(boardService.getBoard().orElseThrow(BoardNotFoundException::new));
    }
}
