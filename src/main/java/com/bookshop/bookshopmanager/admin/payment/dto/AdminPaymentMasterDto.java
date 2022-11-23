package com.bookshop.bookshopmanager.admin.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPaymentMasterDto {
  private String payDt;
  private int payNo;
  private int priceTot;
  private String payKind;
  private String userId;
}
