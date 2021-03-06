package com.enjogu.exchange.rate.route;

import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.dto.ExchangeRateRequestDto;
import org.apache.camel.ExchangeProperty;

import java.util.List;

public interface ExchangeRateProxy {
  String ROUTE_ID = "exchange-rate-route-id";
  String ROUTE = "direct:exchange-rate";
  String REQUEST_DTO = "requestDto";
  String API_KEY = "apiKey";

  List<ExchangeRate> getExchangeRate(@ExchangeProperty(REQUEST_DTO) ExchangeRateRequestDto dto,
      @ExchangeProperty(API_KEY) String apiKey);
}
