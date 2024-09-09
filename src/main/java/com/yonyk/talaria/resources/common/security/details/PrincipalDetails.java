package com.yonyk.talaria.resources.common.security.details;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yonyk.talaria.resources.entity.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

  private final Member member;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(member.getMemberRole().getRole()));
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return this.member.getMemberName();
  }

  public String getEmail() {
    return this.member.getEmail();
  }

  public Member getMember() {
    return member;
  }
}
