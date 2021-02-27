package org.eylem.mybank.controller;

import lombok.SneakyThrows;
import org.eylem.mybank.dto.CardDto;
import org.eylem.mybank.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDto create(@Valid @RequestBody CardDto cardDto){
        return cardService.create(cardDto.toCard()).cardDto();
    }

    @GetMapping("/{id}")
    public CardDto get(@PathVariable("id") UUID id){
        return cardService.get(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Card not found!")).cardDto();
    }

    @GetMapping("/{id}/getdebt")
    public int getDebt(@PathVariable("id") UUID id){
        return cardService.getDebt(id);
    }

    @SneakyThrows
    @PutMapping("/{id}/updatedebt")
    public CardDto updateDebt(@PathVariable("id") UUID id,
                              @RequestParam("money") int money){
        return cardService.updateDebt(id, money);
    }

    @PutMapping("/{id}/paydebt/{accountID}")
    public CardDto payDebt(@PathVariable("id") UUID id,
                           @PathVariable("accountID") UUID accountID,
                           @RequestParam("debt") int debt){
        return cardService.payDebt(id,accountID,debt);
    }
}
