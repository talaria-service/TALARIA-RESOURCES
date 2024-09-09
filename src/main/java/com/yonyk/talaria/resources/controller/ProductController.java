package com.yonyk.talaria.resources.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyk.talaria.resources.common.security.details.PrincipalDetails;
import com.yonyk.talaria.resources.entity.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

  @GetMapping("/test")
  public ResponseEntity<Member> test(@AuthenticationPrincipal PrincipalDetails principalDetails) {
    Member member = principalDetails.getMember();
    return ResponseEntity.ok(member);
  }
}
