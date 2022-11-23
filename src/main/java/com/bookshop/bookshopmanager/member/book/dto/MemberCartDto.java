package com.bookshop.bookshopmanager.member.book.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberCartDto {
  private String isbn;
  private String title;
  private String writer;
  private int totCount;
  private String publisher;
  private int totPrice;
  private String imgUrl;

  public MemberCartDto(MemberBookDto parameter){
    this.isbn = parameter.getIsbn();
    this.title = parameter.getTitle();
    this.writer = parameter.getWriter();
    this.publisher = parameter.getPublisher();
    this.imgUrl = parameter.getImgUrl();
  }
}
