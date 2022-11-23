package com.bookshop.bookshopmanager.admin.main.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecomBookDto {
  private String isbn;

  private String title;
  private String content;
  private String imgUrl;
  private String publisher;
  private String writer;
  private LocalDate regDt;
  private int price;
}
