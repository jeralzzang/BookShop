package com.bookshop.bookshopmanager.member.orderbook.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberOrderBookDto {
  private int no;
  private String title;
  private String writer;
  private String publisher;
  private String imgUrl;

  private int orderCount;
  private int price;
  private LocalDate orderDate;
  private String status;

}
