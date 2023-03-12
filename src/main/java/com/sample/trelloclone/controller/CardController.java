package com.sample.trelloclone.controller;

import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.exception.EmptyCardsException;
import com.sample.trelloclone.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.time.ZonedDateTime;
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
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the matching cards",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardDto.class))}
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content
            )
    })
    public ResponseEntity<List<CardDto>> getCardsByTag(
            @Parameter(description = "Tag of the card", example = "MVP")
            @PathVariable String tag) {
        return getResponseEntity(cardService.getCardsByTag(tag));
    }

    @GetMapping("/column/{column}")
    @Operation(summary = "Get cards by column")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the matching cards",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardDto.class))}
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content
            )
    })
    public ResponseEntity<List<CardDto>> getCardsByColumn(
            @Parameter(description = "Column name", example = "in-progress")
            @PathVariable String column) {
        return getResponseEntity(cardService.getCardsByColumn(column));
    }

    @GetMapping("/createdAfter/{utcDateTime}")
    @Operation(summary = "Get cards created after given UTC date time")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the matching cards",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardDto.class))}
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content
            )
    })
    public ResponseEntity<List<CardDto>> getCardsByColumn(
            @Parameter(description = "Created date time to search after this", example = "2023-03-01T23:32:46Z")
            @PathVariable ZonedDateTime utcDateTime) {
        return getResponseEntity(cardService.getCardsCreatedAfter(utcDateTime));
    }

    private ResponseEntity<List<CardDto>> getResponseEntity(List<CardDto> cardsByColumn) {
        if (CollectionUtils.isEmpty(cardsByColumn)) {
            throw new EmptyCardsException();
        }
        return ResponseEntity.ok(cardsByColumn);
    }
}
