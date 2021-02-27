package org.eylem.mybank.controller;

import org.eylem.mybank.dto.AccountDto;
import org.eylem.mybank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@Valid @RequestBody AccountDto accountDto){
        return accountService.create(accountDto.toAccount()).accountDto();
    }

    @GetMapping("/{id}")
    public AccountDto getAccountSummary(@PathVariable("id")UUID id){
        return accountService.getAccountSummary(id).accountDto();
    }

    @PutMapping("/{IBAN}/{toIBAN}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto transferMoney(@PathVariable("IBAN") String IBAN,
                                    @PathVariable("toIBAN") String toIBAN,
                                    @RequestParam("moneyTransfer") int moneyTransfer){
        return accountService.transferMoney(IBAN,toIBAN, moneyTransfer);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountDto delete(@PathVariable("id")UUID id){
        return accountService.delete(id).accountDto();
    }
}
