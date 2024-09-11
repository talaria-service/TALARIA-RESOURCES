package com.yonyk.talaria.resources.exception.enums;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum OrderExceptionType implements ExceptionType {
  // 400 Bad Request
  ORDER_NOT_FOUND(HttpStatus.BAD_REQUEST, "주문이 존재하지 않습니다."),
  STATUS_CHANGE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "상태를 변경할 수 없습니다.");

  private final HttpStatus status;
  private final String message;

  @Override
  public HttpStatus status() {
    return this.status;
  }

  @Override
  public String message() {
    return this.message;
  }
}
