package com.yonyk.talaria.resources.common.security.util;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberAuditorAware implements AuditorAware<String> {

  // @LastModifiedBy 에서 사용될 사용자 이름을 가져온다
  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      return Optional.of(authentication.getName());
    }
    return Optional.empty();
  }
}
