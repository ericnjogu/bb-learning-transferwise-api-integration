package com.enjogu.exchange.rate.route;

import com.enjogu.exchange.rate.config.EndpointProperties;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRateRoute extends RouteBuilder {
  private final EndpointProperties exchangeRateProps;
  private final String logString = "log:" + getClass() + "?multiline=true&showAll=true";
  @Value("${wise.api-key}")
  private String apiKey;

  @Override
  public void configure() {
    //@formatter:off
    from(ExchangeRateProxy.ROUTE)
      .routeId(ExchangeRateProxy.ROUTE_ID)
      .setHeader(Exchange.HTTP_METHOD, constant(exchangeRateProps.getHttpMethod()))
      .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
      .setHeader("Authorization", constant(String.format("Bearer %s", apiKey)))
      .marshal().json(JsonLibrary.Jackson)
      .to(logString)
      .toD(String.format("%s?source=%s&target=%s&from=%s&to=%s",
          exchangeRateProps.getUrl(), "${exchangeProperty.requestDto.source}",
          "${exchangeProperty.requestDto.target}", "${exchangeProperty.requestDto.dateFrom}",
          "${exchangeProperty.requestDto.dateTo}"))
      .unmarshal().json(JsonLibrary.Jackson)
      .to(logString);
    //@formatter:on
  }
}
