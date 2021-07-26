package com.enjogu.exchange.rate.service.impl;

import com.backbase.buildingblocks.backend.security.auth.config.SecurityContextUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceImplTest {

  private ExchangeRateServiceImpl exchangeRateService;

  @Mock
  private SecurityContextUtil securityContextUtil;

  @BeforeEach
  void setup() {
    exchangeRateService = new ExchangeRateServiceImpl(securityContextUtil);
  }

  @Test
  void addToTime() {
    LocalDateTime result = exchangeRateService.addToTime(LocalDate.parse("2021-06-28"));
    assertTrue(result.format(DateTimeFormatter.ISO_DATE_TIME).startsWith("2021-06-28T23:59:59"));
  }

  @Test
  void addToTime_not_present() {
    LocalDateTime result = exchangeRateService.addToTime(null);
    assertTrue(result.format(DateTimeFormatter.ISO_DATE_TIME).startsWith(LocalDate.now() + "T23:59:59"));
  }

  @Test
  void addFromTime() {
    LocalDateTime result = exchangeRateService.addFromTime(LocalDate.parse("2021-06-28"));
    assertEquals("2021-06-28T00:00:00", result.format(DateTimeFormatter.ISO_DATE_TIME));
  }

  @Test
  void addFromTime_not_present() {
    LocalDateTime result = exchangeRateService.addFromTime(null);
    assertTrue(result.format(DateTimeFormatter.ISO_DATE_TIME).startsWith(LocalDate.now() + "T00:00:00"));
  }
}