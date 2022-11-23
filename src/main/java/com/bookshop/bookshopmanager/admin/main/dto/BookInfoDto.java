package com.bookshop.bookshopmanager.admin.main.dto;

import com.bookshop.bookshopmanager.admin.main.entity.AddBookEntity;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class BookInfoDto {
  private String isbn;
  private String title;
  private String writer;
  private int count;
  private String publisher;
  private int price;
  private String imgUrl;

}
