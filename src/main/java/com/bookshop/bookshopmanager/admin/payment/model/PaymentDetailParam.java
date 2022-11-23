package com.bookshop.bookshopmanager.admin.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailParam {

  private String payDt;
  private String payNo;
}
