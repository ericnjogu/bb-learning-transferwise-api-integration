package com.enjogu.exchange.rate.route;

import com.backbase.buildingblocks.backend.security.auth.config.SecurityContextUtil;
import com.enjogu.exchange.rate.config.EndpointProperties;
import com.enjogu.exchange.rate.route.ssl.NoopX509ExtendedTrustManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.camel.util.jsse.TrustManagersParameters;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.net.ssl.X509ExtendedTrustManager;

@RequiredArgsConstructor
@Component
@Slf4j
public class ExchangeRateRoute extends RouteBuilder {
  private final EndpointProperties exchangeRateProps;
  private final SecurityContextUtil securityContextUtil;

  private final String logString = "log:" + getClass() + "?multiline=true&showAll=true";

  @Override
  public void configure() {
    configureSsl();

    //@formatter:off
    from(ExchangeRateProxy.ROUTE)
      .routeId(ExchangeRateProxy.ROUTE_ID)
      .setHeader(Exchange.HTTP_METHOD, constant(exchangeRateProps.getHttpMethod()))
      .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
      .setHeader("Authorization", simple(String.format("Bearer ${%s}", "exchangeProperty.apiKey")))
      .marshal().json(JsonLibrary.Jackson)
      .to(logString)
      .toD(String.format("%s?source=${%s}&target=${%s}&from=${%s}&to=${%s}",
          exchangeRateProps.getUrl(), "exchangeProperty.requestDto.source",
          "exchangeProperty.requestDto.target", "exchangeProperty.requestDto.dateFrom",
          "exchangeProperty.requestDto.dateTo"))
      .unmarshal().json(JsonLibrary.Jackson)
      .to(logString);
    //@formatter:on
  }

  void configureSsl() {
    var httpComponent = this.getContext().getComponent("https4", HttpComponent.class);
    httpComponent.setX509HostnameVerifier(NoopHostnameVerifier.INSTANCE);
    var trustManagersParameters = new TrustManagersParameters();
    X509ExtendedTrustManager extendedTrustManager = new NoopX509ExtendedTrustManager();
    trustManagersParameters.setTrustManager(extendedTrustManager);
    var sslContextParameters = new SSLContextParameters();
    sslContextParameters.setTrustManagers(trustManagersParameters);
    httpComponent.setSslContextParameters(sslContextParameters);
  }
}
