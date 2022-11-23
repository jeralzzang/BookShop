package com.bookshop.bookshopmanager.member.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberPaymentMapper {
  int getPaymentKey(String payDt);
}
