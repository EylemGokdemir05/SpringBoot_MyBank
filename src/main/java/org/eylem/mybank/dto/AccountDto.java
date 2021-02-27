package org.eylem.mybank.dto;

import lombok.*;
import org.eylem.mybank.enums.AccountType;
import org.eylem.mybank.entity.Account;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class AccountDto {
    private UUID id;
    private String accountNumber;
    private int balance;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String iban;
    private String currency;
    private AccountType accountType;

    public AccountDto() {

    }

    public AccountDto(UUID id, String accountNumber, int balance, LocalDate createdDate, LocalDate modifiedDate, String iban, String currency, AccountType accountType) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.iban = iban;
        this.currency = currency;
        this.accountType = accountType;
    }

    public Account toAccount(){
        return Account.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .balance(this.balance)
                .createdDate(this.createdDate)
                .modifiedDate(this.modifiedDate)
                .iban(this.iban)
                .currency(this.currency)
                .accountType(this.accountType)
                .build();
    }
}
