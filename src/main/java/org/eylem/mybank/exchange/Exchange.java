package org.eylem.mybank.exchange;

import lombok.Data;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Data
public class Exchange {
    public static final Function<String,RestExchangeDto> convertedCurrency=(base->new RestTemplate()
    .getForObject("https://api.exchangeratesapi.io/latest?base="+base,RestExchangeDto.class));
}
