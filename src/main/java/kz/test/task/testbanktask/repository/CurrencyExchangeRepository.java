package kz.test.task.testbanktask.repository;

import kz.test.task.testbanktask.domain.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    Optional<CurrencyExchange> findTopByCurrencyOrderByDateDesc(String currency);
    Optional<CurrencyExchange> findByCurrencyAndDate(String currency, LocalDate date);

}
