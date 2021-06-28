package com.enjogu.exchange.rate.route;

import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.dto.EchangeRateRequestDto;

public interface ExchangeRateProxy {
  ExchangeRate getExchangeRate(EchangeRateRequestDto dto);
}
