package kz.test.task.testbanktask.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyExchangeDto {

    private String currency;
    private BigDecimal rate;

}
