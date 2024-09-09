package com.yonyk.talaria.resources.entity;

import com.yonyk.talaria.resources.entity.enums.MemberRole;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  private String memberName;
  private String email;
  private MemberRole memberRole;
}
