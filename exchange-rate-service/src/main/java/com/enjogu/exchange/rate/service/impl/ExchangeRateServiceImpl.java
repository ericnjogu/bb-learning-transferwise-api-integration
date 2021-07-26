package com.enjogu.exchange.rate.service.impl;

import com.backbase.buildingblocks.backend.security.auth.config.SecurityContextUtil;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.enjogu.exchange.rate.api.service.v2.model.ExchangeRate;
import com.enjogu.exchange.rate.dto.ExchangeRateRequestDto;
import com.enjogu.exchange.rate.route.ExchangeRateProxy;
import com.enjogu.exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Produce;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {
  private final SecurityContextUtil securityContextUtil;
  public static final String USR_COUNTRY = "usr_country";
  public static final String TW_API_KEY = "tw-api-key";

  @Produce(uri = ExchangeRateProxy.ROUTE)
  private ExchangeRateProxy exchangeRateProxy;
  @Override
  public List<ExchangeRate> getExchangeRate(ExchangeRateRequestDto dto) {
    log.debug("user country from token is {}", securityContextUtil.getUserTokenClaim(USR_COUNTRY, String.class));
    return exchangeRateProxy.getExchangeRate(dto, getApiKey());
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

  /**
   * @return the API key
   */
  private String getApiKey() {
    Optional<String> userTokenClaim = securityContextUtil.getUserTokenClaim(TW_API_KEY, String.class);
    if (userTokenClaim.isPresent()) {
      log.debug("api key from token is {}", userTokenClaim.get());
      return userTokenClaim.get();
    } else {
      String msg = "could not retrieve api key from claims";
      log.error(msg);
      throw new BadRequestException(msg);
    }
  }
}
