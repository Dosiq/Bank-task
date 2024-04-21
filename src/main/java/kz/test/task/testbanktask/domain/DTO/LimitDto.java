package kz.test.task.testbanktask.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LimitDto {
    @NotNull
    private BigDecimal limitSum;
    @NotBlank
    private String limitCurrencyShortName;

}
