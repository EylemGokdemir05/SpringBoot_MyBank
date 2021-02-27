package org.eylem.mybank.dto;

import lombok.*;
import org.eylem.mybank.entity.Card;

import java.util.UUID;

@Data
@Builder
public class CardDto {
    private UUID id;
    private String cardNumber;
    private int boundary;
    private int debt;

    public Card toCard(){
        return Card.builder()
                .id(this.id)
                .cardNumber(this.cardNumber)
                .boundary(this.boundary)
                .debt(this.debt)
                .build();
    }
}
