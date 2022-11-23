package com.bookshop.bookshopmanager.member.payment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentKeyParam {
  private String payDt;
  private int payNo;

}
