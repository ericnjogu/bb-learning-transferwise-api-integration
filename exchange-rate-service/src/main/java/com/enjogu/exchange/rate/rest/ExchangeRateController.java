package com.enjogu.exchange.rate.rest;

import com.enjogu.exchange.fee.listener.client.v1.fees.ExchangeFeeExchangeRateFeesClient;
import com.enjogu.exchange.rate.api.service.v2.ExchangeRateApi;
import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.api.service.v2.model.Fee;
import com.enjogu.exchange.rate.dto.ExchangeRateRequestDto;
import com.enjogu.exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ExchangeRateController implements ExchangeRateApi {
  private final ExchangeRateService exchangeRateService;
  private final ExchangeFeeExchangeRateFeesClient feesClient;

  @Override
  public ResponseEntity<List<ExchangeRate>> getExchangeRates(String source, String target, LocalDate from,
      LocalDate to) {
    //@formatter:off
    ExchangeRateRequestDto request = ExchangeRateRequestDto
        .builder()
        .source(source)
        .target(target)
        .from(exchangeRateService.addFromTime(from))
        .to(exchangeRateService.addToTime(to))
        .build();
    //@formatter:on
    return ResponseEntity.of(Optional.of(exchangeRateService.getExchangeRate(request)));
  }

  @Override
  public ResponseEntity<Fee> getFee() {
    return ResponseEntity.of(Optional.of(new Fee().fee(
        Objects.requireNonNull(feesClient.getExchangeRateFees().getBody()).getFee())));
  }

}
