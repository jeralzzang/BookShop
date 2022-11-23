package com.bookshop.bookshopmanager.member.sign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberModifyParam {
  private String id;
  private String nickName;
  private String password;
  private String rePassword;
  private int no;
}
