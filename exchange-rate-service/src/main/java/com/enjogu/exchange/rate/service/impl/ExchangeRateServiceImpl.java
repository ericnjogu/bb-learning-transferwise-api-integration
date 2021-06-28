package com.enjogu.exchange.rate.service.impl;

import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.dto.ExchangeRateRequestDto;
import com.enjogu.exchange.rate.route.ExchangeRateProxy;
import com.enjogu.exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Produce;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

  @Produce(uri = ExchangeRateProxy.ROUTE)
  private ExchangeRateProxy exchangeRateProxy;
  @Override
  public List<ExchangeRate> getExchangeRate(ExchangeRateRequestDto dto) {
    return exchangeRateProxy.getExchangeRate(dto);
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
