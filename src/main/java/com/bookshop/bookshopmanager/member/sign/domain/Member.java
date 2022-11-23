package com.bookshop.bookshopmanager.member.sign.domain;

import com.bookshop.bookshopmanager.member.sign.type.MemberStatus;
import com.sun.istack.NotNull;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int no;
  @NotNull
  private String id;
  private String password;
  private String nickName;

  private LocalDate regDt;
  private LocalDate udtDt;
  private MemberStatus status;

  private boolean adminYn;

}
