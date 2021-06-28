package com.enjogu.exchange.rate.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class ExchangeRateRequestDto {
  private LocalDateTime from;
  private LocalDateTime to;
  private String source;
  private String target;

  public String getDateFrom() {
    return from.format(DateTimeFormatter.ISO_DATE_TIME);
  }

  public String getDateTo() {
    return to.format(DateTimeFormatter.ISO_DATE_TIME);
  }
}
