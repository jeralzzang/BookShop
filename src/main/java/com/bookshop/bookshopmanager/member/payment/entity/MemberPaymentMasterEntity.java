package com.bookshop.bookshopmanager.member.payment.entity;

import com.bookshop.bookshopmanager.member.payment.model.MemberPaymentMasterId;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PAY_MASTER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@IdClass(MemberPaymentMasterId.class)
public class MemberPaymentMasterEntity implements Serializable {
  @Id
  @Column(name = "payDt")
  private String payDt;
  @Id
  @Column(name ="payNo")
  private int payNo;

  private int priceTot;
  private String payKind;
  private String result;
  private int userNo;
  private LocalDate regDt;
  private String payInfo;
  private String resultCode;

}
