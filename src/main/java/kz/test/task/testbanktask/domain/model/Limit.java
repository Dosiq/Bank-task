package kz.test.task.testbanktask.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "limits")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Limit {

    public Limit(BigDecimal limitSum, LocalDateTime limitDateTime, String currency) {
        this.limitSum = limitSum;
        this.limitDateTime = limitDateTime;
        this.limitCurrencyShortName = currency;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_sum")
    private BigDecimal limitSum;

    @Column(name = "limit_datetime")
    private LocalDateTime limitDateTime;

    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortName;

    @Column(name = "expense_category")
    private String expenseCategory;
}
