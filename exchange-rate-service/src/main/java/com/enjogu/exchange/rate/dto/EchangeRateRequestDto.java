package com.enjogu.exchange.rate.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EchangeRateRequestDto {
  private LocalDateTime from;
  private LocalDateTime to;
  private String source;
  private String target;
}
