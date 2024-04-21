package kz.test.task.testbanktask.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "limits")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_sum")
    private BigDecimal limitSum;

    @Column(name = "limit_datetime")
    private LocalDateTime limitDateTime;

    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortName;

}
