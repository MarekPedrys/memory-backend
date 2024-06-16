package pl.wszib.memoryapi.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wszib.memoryapi.services.CardService;
import pl.wszib.memoryapi.web.models.CardRequest;
import pl.wszib.memoryapi.web.models.CardResponse;

import java.util.List;

@RestController
@RequestMapping("categories/{categoryId}/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@PathVariable Long categoryId, @RequestBody CardRequest request) {
        CardResponse cardResponse = cardService.createCard(categoryId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardResponse);
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> cardsList(@PathVariable Long categoryId) {
        List<CardResponse> cards = cardService.fetchAll(categoryId);
        return ResponseEntity.ok(cards);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> removeCard(@PathVariable Long categoryId, @PathVariable Long cardId) {
        cardService.removeCard(categoryId, cardId);
        return ResponseEntity.noContent().build();
    }

}
