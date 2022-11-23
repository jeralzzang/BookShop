package com.bookshop.bookshopmanager.openapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParam {
  private String searchType;
  private String searchValue;
}
