package com.bookshop.bookshopmanager.admin.payment.service;

import com.bookshop.bookshopmanager.admin.payment.dto.AdminPaymentDetailDto;
import com.bookshop.bookshopmanager.admin.payment.dto.AdminPaymentMasterDto;
import com.bookshop.bookshopmanager.admin.payment.mapper.AdminPaymentMapper;
import com.bookshop.bookshopmanager.admin.payment.model.PaymentDetailParam;
import com.bookshop.bookshopmanager.admin.payment.model.PaymentMasterParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPaymentService {

  private final AdminPaymentMapper adminPaymentMapper;

  //결제 상세정보 가져오기
  public List<AdminPaymentDetailDto> getPaymentDetailList(PaymentDetailParam parameter){
    return adminPaymentMapper.getPaymentDetailList(parameter.getPayDt(), parameter.getPayNo());
  }

  public List<AdminPaymentMasterDto> getpaymentMasterList(PaymentMasterParam parameter) {
    return adminPaymentMapper.getPaymentMasterList(parameter);
  }

  public long getPaymentCount(PaymentMasterParam parameter) {
    return adminPaymentMapper.getPaymentCount(parameter);
  }

  public AdminPaymentMasterDto setPaykind(AdminPaymentMasterDto parameter){
    String paykind = parameter.getPayKind();
    String setPaykind;
    if(paykind.equals("card")){
      setPaykind = "카드";
    }else if(paykind.equals("npay")){
      setPaykind ="네이버페이";
    }else if(paykind.equals("kpay")){
      setPaykind = "카카오페이";
    }else{
      setPaykind ="핸드폰결제";
    }
    parameter.setPayKind(setPaykind);
    return parameter;
  }
}
