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

@Entity(name = "BOOK_RECOM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecomBookEntity {
  @Id
  private String isbn;
  private int no;
  private LocalDate regDt;
  private String content;
}
