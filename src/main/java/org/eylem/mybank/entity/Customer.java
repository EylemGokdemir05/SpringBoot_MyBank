package org.eylem.mybank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eylem.mybank.dto.CustomerDto;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    @OneToMany(mappedBy = "customer")
    private Set<Card> cards;

    public CustomerDto customerDto(){
        return CustomerDto.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .accounts(accounts.stream()
                        .map(Account::accountDto)
                        .collect(Collectors.toSet()))
                .cards(cards.stream()
                        .map(Card::cardDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
