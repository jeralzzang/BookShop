package com.bookshop.bookshopmanager.openapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseParam {
  private String isbn;
  private String title;
  private String writer;
  private String publisher;
  private int price;
  private String imgUrl;
}
