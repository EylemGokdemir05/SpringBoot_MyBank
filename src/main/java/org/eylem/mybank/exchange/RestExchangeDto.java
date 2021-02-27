package org.eylem.mybank.exchange;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;

@Data
@Builder
public class RestExchangeDto {
    private HashMap<String, Double> rates;
    private String base;
    private LocalDate date;

}
