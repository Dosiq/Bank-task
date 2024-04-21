package kz.test.task.testbanktask.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {

    private String accountFrom;
    private String accountTo;
    private String currencyShortName;
    private BigDecimal sum;
    private String expenseCategory;
    private String datetime;
    private Boolean limitExceeded;

}

