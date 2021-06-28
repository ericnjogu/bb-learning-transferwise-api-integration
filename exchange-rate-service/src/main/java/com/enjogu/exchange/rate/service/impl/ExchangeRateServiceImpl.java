package com.enjogu.exchange.rate.service.impl;

import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.dto.EchangeRateRequestDto;
import com.enjogu.exchange.rate.service.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
  @Override
  public List<ExchangeRate> getExchangeRate(EchangeRateRequestDto dto) {
    return Collections.emptyList();
  }

  @Override
  public LocalDateTime addToTime(LocalDate to) {
    if (Objects.isNull(to)) {
      return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    } else {
      return LocalDateTime.of(to, LocalTime.MAX);
    }
  }

  @Override
  public LocalDateTime addFromTime(LocalDate from) {
    if (Objects.isNull(from)) {
      return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    } else {
      return LocalDateTime.of(from, LocalTime.MIN);
    }
  }
}
