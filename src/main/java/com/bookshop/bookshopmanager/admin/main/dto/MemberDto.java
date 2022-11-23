package com.bookshop.bookshopmanager.admin.main.dto;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
  private int no;
  private String id;
  private String nickName;

  private LocalDate regDt;
  private LocalDate udtDt;

  private String status;
  private boolean adminYn;

}
