package com.enjogu.exchange.rate.rest;

import com.backbase.buildingblocks.backend.security.auth.config.SecurityContextUtil;
import com.enjogu.exchange.rate.config.IntegrationTest;
import com.enjogu.exchange.rate.service.impl.ExchangeRateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.status;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@IntegrationTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ExchangeRateControllerTestIT {
  private final MockMvc mvc;

  public static final String TEST_JWT =
      "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxk"
          + "ZXIiLCJpYXQiOjE0ODQ4MjAxOTYsImV4cCI6MTUxNjM1NjE5NiwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJv"
          + "Y2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2Nr"
          + "ZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXSwiaW51aWQiOiJKaW1te"
          + "SJ9.O9TE28ygrHmDjItYK6wRis6wELD5Wtpi6ekeYfR1WqM";

  @MockBean
  private SecurityContextUtil securityContextUtil;

  @BeforeEach
  void setUp() {
    when(securityContextUtil.getUserTokenClaim(eq(ExchangeRateServiceImpl.TW_API_KEY), any())).thenReturn(
        Optional.of("test-api-token")
    );
  }

  @Test
  void getExchangeRates() throws Exception {
    String from = "2019-07-01";
    String to = "2019-07-01";
    String source = "EUR";
    String target = "USD";

    String content = IOUtils.resourceToString("/json/response/transferwise-exchange-rate.json",
        StandardCharsets.UTF_8);

    stubFor(get(urlMatching("/v1/rates.*"))
        .withQueryParam("from", equalTo(String.format("%sT00:00:00", from)))
        .withQueryParam("to", matching(String.format("%sT23:59:59.*", to)))
        .withQueryParam("source", equalTo(source))
        .withQueryParam("target", equalTo(target))
        .withHeader("Authorization", equalTo(String.format("Bearer %s", "test-api-token")))
        .willReturn(status(HttpStatus.OK.value()).withBody(content))
    );

    mvc.perform(MockMvcRequestBuilders.get(
        "/client-api/v1/exchange-rates?from={from}&to={to}&source={source}&target={target}",
        from, to, source, target)
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", TEST_JWT)
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().json(content))
        .andDo(print());
  }
}