package kz.test.task.testbanktask.controller;

import kz.test.task.testbanktask.domain.DTO.LimitDto;
import kz.test.task.testbanktask.service.LimitService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LimitController.class)
public class LimitControllerTests {

    @Autowired
    private LimitController limitController;

    @MockBean
    private LimitService limitService;

    @Test
    public void setLimit_ShouldReturnStatusOk_WhenLimitIsSet() {
        LimitDto limitDto = new LimitDto();
        limitDto.setLimitSum(new BigDecimal("1000"));
        limitDto.setLimitCurrencyShortName("USD");
        doNothing().when(limitService).setNewLimit(limitDto);
        ResponseEntity<Void> response = limitController.setLimit(limitDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
