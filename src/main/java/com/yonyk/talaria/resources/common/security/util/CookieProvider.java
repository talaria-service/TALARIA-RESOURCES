package com.yonyk.talaria.resources.common.security.util;

import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// 쿠키 관리 클래스
@Slf4j
@Component
public class CookieProvider {
  // 리프레시 토큰 쿠키의 이름
  @Value("${cookie.refresh-token.cookie-name}")
  String cookieName;

  // 리퀘스트에서 리프레시 토큰 빼오기
  public Optional<Cookie> getRefreshTokenCookie(HttpServletRequest request) {
    return Arrays.stream(request.getCookies())
        .filter(cookie -> cookieName.equals(cookie.getName()))
        .findFirst();
  }
}
