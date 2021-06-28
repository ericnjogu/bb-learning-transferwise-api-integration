package com.enjogu.exchange.rate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferwiseEndpoints {
  @Bean
  @ConfigurationProperties(prefix = "wise.endpoints.exchange-rate-get")
  EndpointProperties exchangeRateProps() {
    return new EndpointProperties();
  }
}
