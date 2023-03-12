package com.sample.trelloclone.controller;

import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.exception.EmptyCardsException;
import com.sample.trelloclone.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/tag/{tag}")
    @Operation(summary = "Get cards by tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the matching cards",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardDto.class))}),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)
    })
    public ResponseEntity<List<CardDto>> getCardsByTag(@PathVariable(required = true) String tag) {
        List<CardDto> cardsByTag = cardService.getCardsByTag(tag);
        if (CollectionUtils.isEmpty(cardsByTag)) {
            throw new EmptyCardsException();
        }
        return ResponseEntity.ok(cardsByTag);
    }
}
