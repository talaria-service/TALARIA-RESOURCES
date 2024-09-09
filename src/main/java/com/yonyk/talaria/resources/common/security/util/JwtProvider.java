package com.yonyk.talaria.resources.common.security.util;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

  // 액세스 토큰이 저장될 헤더 필드 이름
  @Value("${jwt.access-token-header}")
  public String accessTokenHeader;

  // 액세스 토큰 앞에 지정될 프리픽스
  @Value("${jwt.prefix}")
  public String prefix;

  // 액세스 토큰 가져오기
  public String getAccessToken(HttpServletRequest request) {
    // 헤더에서 토큰 가져오기
    return request.getHeader(accessTokenHeader);
  }

  // 인증 객체 리턴
  //  public Authentication getAuthentication(String token) {
  //    // accessToken에서 prefix 제거
  //    String accessToken = token.substring(prefix.length());
  //    // 액세스 토큰 해석하여 정보 가져오기
  //    Claims claims =
  //        Jwts.parser().verifyWith(key).build().parseSignedClaims(accessToken).getPayload();
  //    // 액세스 토큰에 담긴 memberId로 PrincipalDetails 객체 가져오기
  //    UserDetails userDetails = principalDetailsService.loadUserByUsername(claims.getSubject());
  //    // 가져온 객체를 이용해 인증객체 만들기
  //    return new UsernamePasswordAuthenticationToken(userDetails, null,
  // userDetails.getAuthorities());
  //  }
}
