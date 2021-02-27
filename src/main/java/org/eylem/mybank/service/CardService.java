package org.eylem.mybank.service;


import org.eylem.mybank.dto.AccountDto;
import org.eylem.mybank.dto.CardDto;
import org.eylem.mybank.entity.Card;
import org.eylem.mybank.repository.AccountRepo;
import org.eylem.mybank.repository.CustomerRepo;
import org.eylem.mybank.repository.CardRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {
    private final CardRepo cardRepo;
    private final AccountRepo accountRepo;
    private final CustomerRepo customerRepo;

    public CardService(CardRepo cardRepo, AccountRepo accountRepo, CustomerRepo customerRepo) {
        this.cardRepo = cardRepo;
        this.accountRepo = accountRepo;
        this.customerRepo = customerRepo;
    }

    public Card create(Card card) {
        return cardRepo.save(card);
    }

    public Optional<Card> get(UUID id) {
        return cardRepo.findById(id);
    }

    public int getDebt(UUID id) {
        return cardRepo.findById(id).get().getDebt();
    }

    public CardDto updateDebt(UUID id, int money) throws Exception {
        CardDto cardDto = cardRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card is not found!")).cardDto();
        if (cardDto.getBoundary() > cardDto.getDebt()) {
            cardDto.setDebt(cardDto.getDebt() + money);
            return cardRepo.save(cardDto.toCard()).cardDto();
        } else {
            throw new Exception("Limit not enough!");
        }
    }

    public CardDto payDebt(UUID id, UUID accountID, int debt) {
        AccountDto accountDto = accountRepo.findById(accountID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found!")).accountDto();
        int balance = accountDto.getBalance();
        CardDto cardDto = cardRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card is not found!")).cardDto();
        if (balance > debt && debt != 0) {
            accountDto.setBalance(accountDto.getBalance() - debt);
            cardDto.setDebt(cardDto.getDebt() - debt);
        }
        return cardRepo.save(cardDto.toCard()).cardDto();
    }
}
