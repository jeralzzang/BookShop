package com.bookshop.bookshopmanager.admin.payment.mapper;

import com.bookshop.bookshopmanager.admin.payment.dto.AdminPaymentDetailDto;
import com.bookshop.bookshopmanager.admin.payment.dto.AdminPaymentMasterDto;
import com.bookshop.bookshopmanager.admin.payment.model.PaymentMasterParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminPaymentMapper {

  List<AdminPaymentDetailDto> getPaymentDetailList(String payDt, String payNo);
  List<AdminPaymentMasterDto> getPaymentMasterList(PaymentMasterParam parameter);
  long getPaymentCount(PaymentMasterParam parameter);
}
