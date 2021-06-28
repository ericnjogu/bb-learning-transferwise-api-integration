package com.enjogu.exchange.rate.route;

import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.dto.EchangeRateRequestDto;
import org.apache.camel.ExchangeProperty;

import java.util.List;

public interface ExchangeRateProxy {
  String ROUTE_ID = "exchange-rate-route-id";
  String ROUTE = "direct:exchange-rate";
  String REQUEST_DTO = "requestDto";

  List<ExchangeRate> getExchangeRate(@ExchangeProperty(REQUEST_DTO) EchangeRateRequestDto dto);
}
