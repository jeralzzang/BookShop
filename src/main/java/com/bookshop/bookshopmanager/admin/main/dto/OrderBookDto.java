package com.bookshop.bookshopmanager.admin.main.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookDto {
  private int no;

  private String id;
  private String nickName;
  private String bookName;
  private String isbn;
  private int count;
  private LocalDate orderDate;
  private String status;

  private int userNo;
}
