package com.bookshop.bookshopmanager.member.payment.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberPaymentMasterId implements Serializable{

  private String payDt;
  private int payNo;
}
