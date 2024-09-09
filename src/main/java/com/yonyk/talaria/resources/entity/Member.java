package com.yonyk.talaria.resources.entity;

import com.yonyk.talaria.resources.entity.enums.MemberRole;
import com.yonyk.talaria.resources.grpc.AuthorizationProto.*;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  private String memberName;
  private String email;
  private MemberRole memberRole;

  public static Member toMember(AuthResponse response) {
    return Member.builder()
        .memberName(response.getMemberName())
        .memberRole(
            response.getMemberRole(0).equals("ROLE_ADMIN") ? MemberRole.ADMIN : MemberRole.USER)
        .email(response.getEmail())
        .build();
  }
}
