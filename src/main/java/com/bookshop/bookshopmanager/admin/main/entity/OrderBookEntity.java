package com.bookshop.bookshopmanager.admin.main.entity;


import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BOOK_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderBookEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int no;

  private String isbn;
  private int count;
  private LocalDate orderDate;
  private String status;
  private int userNo;
}
