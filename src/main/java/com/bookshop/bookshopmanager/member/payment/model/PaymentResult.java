package com.bookshop.bookshopmanager.member.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentResult {
  SUCCESS ("결제 요청 결과 -> 성공","0000"),
  FAIL("결제 요청 결과 -> 실패","9999");

  private final String message;
  private final String code;
}
