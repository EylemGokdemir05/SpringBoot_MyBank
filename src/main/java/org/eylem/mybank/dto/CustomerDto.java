package org.eylem.mybank.dto;

import lombok.*;
import org.eylem.mybank.dto.AccountDto;
import org.eylem.mybank.dto.CardDto;
import org.eylem.mybank.entity.Customer;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CustomerDto {
    private UUID id;
    @NotBlank(message = "Name is necessary")
    private String name;
    @NotBlank(message = "Surname is necessary")
    private String surname;
    private Set<AccountDto> accounts;
    private Set<CardDto> cards;

    public Customer toCustomer(){
        return Customer.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                /*.accounts(accounts.stream()
                    .map(AccountDto::toAccount)
                    .collect(Collectors.toSet()))
                .cards(cards.stream()
                    .map(CardDto::toCard)
                    .collect(Collectors.toSet()))*/
                .build();
    }
}
