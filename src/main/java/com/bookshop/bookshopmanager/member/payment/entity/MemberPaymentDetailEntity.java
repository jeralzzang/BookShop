package com.bookshop.bookshopmanager.member.payment.entity;

import com.bookshop.bookshopmanager.member.payment.model.MemberPaymentDetailId;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "PAY_DETAIL")
@IdClass(MemberPaymentDetailId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPaymentDetailEntity implements Serializable {
  @Id
  @Column(name="payDt")
  private String payDt;
  @Id
  @Column(name="payNo")
  private int payNo;
  @Id
  @Column(name ="paySeq")
  private int paySeq;

  private String isbn;
  private int price;
  private LocalDate regDt;
  private int count;

}
