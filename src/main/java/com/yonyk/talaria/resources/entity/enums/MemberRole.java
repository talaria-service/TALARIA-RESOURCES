package com.yonyk.talaria.resources.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER");

  private String role;
}
