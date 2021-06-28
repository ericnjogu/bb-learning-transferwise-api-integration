package com.enjogu.exchange.rate.route;

import com.enjogu.exchange.rate.config.EndpointProperties;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRateRoute extends RouteBuilder {
  private final EndpointProperties exchangeRateProps;
  @Override
  public void configure() {
    //@formatter:off
    from(ExchangeRateProxy.ROUTE)
      .routeId(ExchangeRateProxy.ROUTE_ID)
      .setHeader(Exchange.HTTP_METHOD, constant(exchangeRateProps.getHttpMethod()))
      .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
      .toD(String.format("%s?source=%s&target=%s&from=%s&to=%s",
          exchangeRateProps.getUrl(), "${exchangeProperty.requestDto.source}",
          "${exchangeProperty.requestDto.target}", "${exchangeProperty.requestDto.from}",
          "${exchangeProperty.requestDto.to}"))
      .unmarshal();
    //@formatter:on
  }
}
