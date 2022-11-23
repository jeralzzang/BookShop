package com.bookshop.bookshopmanager.admin.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPaymentDetailDto {
  private String payDt;
  private String isbn;
  private int price;
  private int count;

  private String title;
  private String writer;
  private String publisher;
}
