package com.nxp.trustid.websocketserver.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.google.inject.servlet.GuiceFilter;

/**
 * Only REST requests are forwarded to the {@link GuiceFilter}, WebSocket requests are not.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class TrustIdPeroGuiceFilter extends GuiceFilter {

  private static final String WEB_SOCKET_URL_PATTERN = "/";

  @Override
  public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws
      IOException, ServletException {
    final HttpServletRequest request = (HttpServletRequest) servletRequest;
    final String requestURI = request.getRequestURI();
    final boolean isWebSocketRequest = (requestURI.equals(WEB_SOCKET_URL_PATTERN));

    if (isWebSocketRequest) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    super.doFilter(servletRequest, servletResponse, filterChain);
  }
}
