package com.enjogu.exchange.rate.service;

import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.dto.ExchangeRateRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeRateService {
  List<ExchangeRate> getExchangeRate(ExchangeRateRequestDto dto);

  /**
   * add time to 'end' date
   * @param to - end date
   * @return date time
   */
  LocalDateTime addToTime(LocalDate to);

  /**
   * add time to 'start' date
   * @param from - start date
   * @return date time
   */
  LocalDateTime addFromTime(LocalDate from);
}
