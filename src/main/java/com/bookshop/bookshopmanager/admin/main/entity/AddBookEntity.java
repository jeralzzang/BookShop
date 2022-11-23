package com.bookshop.bookshopmanager.admin.main.entity;

import com.bookshop.bookshopmanager.admin.main.dto.BookInfoDto;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "BOOK")
@NoArgsConstructor
@Getter
@Setter
public class AddBookEntity {
  @Id
  private String isbn;

  private String title;
  private String writer;
  private int count;
  private String publisher;
  private int price;
  private String imgUrl;
  private boolean recomYn;

  public AddBookEntity(BookInfoDto bookInfoDto){
    this.isbn = bookInfoDto.getIsbn();
    this.title = bookInfoDto.getTitle();
    this.writer = bookInfoDto.getWriter();
    this.count = 0;
    this.publisher = bookInfoDto.getPublisher();
    this.price = bookInfoDto.getPrice();
    this.imgUrl = bookInfoDto.getImgUrl();
  }
}
