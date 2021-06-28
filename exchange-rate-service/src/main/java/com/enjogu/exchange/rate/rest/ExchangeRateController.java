package com.enjogu.exchange.rate.rest;

import com.enjogu.exchange.rate.api.service.v2.ExchangeRateApi;
import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.dto.EchangeRateRequestDto;
import com.enjogu.exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ExchangeRateController implements ExchangeRateApi {
  private final ExchangeRateService exchangeRateService;

  @Override
  public ResponseEntity<List<ExchangeRate>> getExchangeRates(String source, String target, LocalDate from,
      LocalDate to) {
    //@formatter:off
    EchangeRateRequestDto request = EchangeRateRequestDto
        .builder()
        .source(source)
        .target(target)
        .from(exchangeRateService.addFromTime(from))
        .to(exchangeRateService.addToTime(to))
        .build();
    //@formatter:on
    return ResponseEntity.of(Optional.of(exchangeRateService.getExchangeRate(request)));
  }

}
