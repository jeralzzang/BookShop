package com.bookshop.bookshopmanager.admin.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookStatusInput {

  private int no;
  private String orderStatus;

}
