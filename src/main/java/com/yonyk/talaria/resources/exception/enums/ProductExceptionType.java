package com.yonyk.talaria.resources.exception.enums;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProductExceptionType implements ExceptionType {
  // 400 Bad Request
  PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "제품이 존재하지 않습니다."),
  OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다. 주문 정보를 다시 확인해주십시오.");

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
