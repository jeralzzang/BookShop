package com.bookshop.bookshopmanager.member.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberBookDto {
  private String isbn;
  private String title;
  private String writer;
  private int count;
  private String publisher;
  private int price;
  private String imgUrl;
}
