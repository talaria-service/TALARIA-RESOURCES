package com.yonyk.talaria.resources.common.security.handler;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.yonyk.talaria.resources.exception.enums.SecurityExceptionType;

import lombok.extern.slf4j.Slf4j;

// 로그인 후 이용가능한 요청 했을 때 사용되는 핸들러
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final SecurityExceptionHandler securityExceptionHandler;

  public CustomAuthenticationEntryPoint(SecurityExceptionHandler securityExceptionHandler) {
    this.securityExceptionHandler = securityExceptionHandler;
  }

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {

    // 예외 종류 확인
    Exception e = (Exception) request.getAttribute("exception");
    String message = "";

    if (e == null) {
      message = SecurityExceptionType.REQUIRED_AUTHENTICATION.getMessage();
    } else {
      message = securityExceptionHandler.getExceptionMessage(e);
    }

    // 클라이언트에게 응답
    securityExceptionHandler.sendResponse(message, HttpStatus.UNAUTHORIZED, response);
  }
}
