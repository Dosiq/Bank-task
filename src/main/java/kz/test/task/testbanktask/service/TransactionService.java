package kz.test.task.testbanktask.service;

import kz.test.task.testbanktask.domain.DTO.TransactionDto;
import kz.test.task.testbanktask.domain.model.Limit;
import kz.test.task.testbanktask.domain.model.Transaction;
import kz.test.task.testbanktask.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LimitService limitService;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    public void processTransaction(TransactionDto transactionDto) {
        BigDecimal rate = currencyExchangeService.getExchangeRate(transactionDto.getCurrencyShortName());
        BigDecimal amountInUSD = transactionDto.getSum().multiply(rate);
        Limit currentLimit = limitService.getCurrentLimit();

        Transaction transaction = new Transaction();
        transaction.setAccountFrom(transactionDto.getAccountFrom());
        transaction.setAccountTo(transactionDto.getAccountTo());
        transaction.setCurrencyShortName(transactionDto.getCurrencyShortName());
        transaction.setSum(transactionDto.getSum());
        transaction.setExpenseCategory(transactionDto.getExpenseCategory());
        transaction.setDateTime(LocalDateTime.parse(transactionDto.getDatetime()));

        if (amountInUSD.compareTo(currentLimit.getLimitSum()) > 0) {
            transaction.setLimitExceeded(true);
        } else {
            transaction.setLimitExceeded(false);
        }

        transactionRepository.save(transaction);
    }

}
