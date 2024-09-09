package com.yonyk.talaria.resources.common.security.util;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.yonyk.talaria.resources.common.security.details.PrincipalDetails;
import com.yonyk.talaria.resources.entity.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationService {

  // 액세스 토큰이 저장될 헤더 필드 이름
  @Value("${jwt.access-token-header}")
  public String accessTokenHeader;

  // 액세스 토큰 가져오기
  public String getAccessToken(HttpServletRequest request) {
    // 헤더에서 토큰 가져오기
    return request.getHeader(accessTokenHeader);
  }

  // 인증 객체 리턴
  public Authentication getAuthentication(Member member) {
    UserDetails userDetails = new PrincipalDetails(member);
    // 가져온 객체를 이용해 인증객체 만들기
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
}
