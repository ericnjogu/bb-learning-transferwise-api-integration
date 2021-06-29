package com.enjogu.exchange.rate.route.ssl;

import org.apache.commons.lang.NotImplementedException;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import java.net.Socket;
import java.security.cert.X509Certificate;

/**
 * A dummy implementation of Trust manager that accepts everything.
 */
public class NoopX509ExtendedTrustManager extends X509ExtendedTrustManager {

  private static final String MSG_NOT_IMPLEMENTED = "not implemented";

  @Override
  public X509Certificate[] getAcceptedIssuers() {
    return new X509Certificate[0];
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType) {
    throw new NotImplementedException(MSG_NOT_IMPLEMENTED);
  }

  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType) {
    throw new NotImplementedException(MSG_NOT_IMPLEMENTED);
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
    // without implementation
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) {
    // without implementation
  }

  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
    // without implementation
  }

  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) {
    // without implementation
  }

}
