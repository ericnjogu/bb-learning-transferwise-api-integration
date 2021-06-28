package com.enjogu.exchange.rate.rest;

import com.enjogu.exchange.rate.config.IntegrationTest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@IntegrationTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ExchangeRateControllerTestIT {
  private final MockMvc mvc;

  @BeforeEach
  void setUp() {
  }

  @Test
  void getExchangeRates() throws Exception {
    String from = "2019-07-01";
    String to = "2019-07-01";
    String source = "EUR";
    String target = "USD";

    String content = IOUtils.resourceToString("/json/response/transferwise-exchange-rate.json",
        StandardCharsets.UTF_8);
    stubFor(get("/v1/rates")
        .withQueryParam("from", equalTo(String.format("%sT00:00:00", from)))
        .withQueryParam("to", equalTo(String.format("%sT23:59:59", to)))
        .withQueryParam("source", equalTo(source))
        .withQueryParam("target", equalTo(target))
        .withHeader("Authorization", equalTo(String.format("Bearer %s", "test-token")))
        .willReturn(status(HttpStatus.OK.value()).withBody(content))
    );

    mvc.perform(MockMvcRequestBuilders.get(
        "/client-api/v1/exchange-rates?from={from}to={to}&source={source}&target={target}",
        from, to, source, target)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().json(content));
  }
}