package org.eylem.mybank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eylem.mybank.dto.AccountDto;
import org.eylem.mybank.enums.AccountType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue
    private UUID id;
    private String accountNumber;
    private int balance;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modifiedDate;
    private String iban;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Card> cards;

    public AccountDto accountDto(){
        return AccountDto.builder()
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
