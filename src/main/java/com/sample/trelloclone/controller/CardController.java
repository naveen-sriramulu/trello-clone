package com.sample.trelloclone.controller;

import com.sample.trelloclone.dto.CardDto;
import com.sample.trelloclone.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<CardDto>> getCardsByTag(@PathVariable(required = true) String tag) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
