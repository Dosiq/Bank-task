package kz.test.task.testbanktask.service;

import kz.test.task.testbanktask.domain.DTO.LimitDto;
import kz.test.task.testbanktask.domain.DTO.TransactionDto;
import kz.test.task.testbanktask.domain.model.Limit;
import kz.test.task.testbanktask.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LimitService {

    @Autowired
    private LimitRepository limitRepository;

    public List<TransactionDto> findExceededTransactions() {
        // Возвращает список транзакций с флагом limit_exceeded
        return new ArrayList<>();
    }

    public void setNewLimit(LimitDto limitDto) {
        Limit limit = new Limit();
        limit.setLimitSum(limitDto.getLimitSum());  // Используем BigDecimal напрямую, без нового конструктора
        limit.setLimitDateTime(LocalDateTime.now());  // Устанавливаем текущую дату
        limit.setLimitCurrencyShortName(limitDto.getLimitCurrencyShortName());
        limitRepository.save(limit);
    }

    public Limit getCurrentLimit() {
        return limitRepository.findTopByOrderByIdDesc().orElseThrow(() -> new RuntimeException("No limit found"));
    }

    public void updateLimit(Long id, LimitDto limitDto) {
        Limit limit = limitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Limit with id " + id + " not found"));
        limit.setLimitSum(limitDto.getLimitSum());
        limit.setLimitCurrencyShortName(limitDto.getLimitCurrencyShortName());
        // Не изменяем дату, так как это дата установки лимита
        limitRepository.save(limit);
    }

    public void deleteLimit(Long id) {
        if (!limitRepository.existsById(id)) {
            throw new IllegalArgumentException("Limit with id " + id + " not found");
        }
        limitRepository.deleteById(id);
    }

    public List<Limit> getAllLimits() {
        return limitRepository.findAll();
    }

}
