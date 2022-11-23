package com.bookshop.bookshopmanager.member.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartParam {
  private int bookCount;
  private int cartCount;
  private String isbn;
  private int price;

  String searchType;
  String searchValue;


  public String getQueryString() {

    StringBuilder sb = new StringBuilder();

    if (searchType != null && searchType.length() > 0) {
      sb.append(String.format("searchType=%s", searchType));
    }

    if (searchValue != null && searchValue.length() > 0) {
      if (sb.length() > 0) {
        sb.append("&");
      }
      sb.append(String.format("searchValue=%s", searchValue));
    }

    return sb.toString();
  }
}
