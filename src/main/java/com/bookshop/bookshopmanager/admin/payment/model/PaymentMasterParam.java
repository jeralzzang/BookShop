package com.bookshop.bookshopmanager.admin.payment.model;

import com.bookshop.bookshopmanager.admin.main.model.CommonParam;
import com.bookshop.bookshopmanager.member.payment.model.PaymentResult;
import lombok.Data;

@Data
public class PaymentMasterParam implements CommonParam {

  long pageIndex;
  long pageSize;

  String searchType;
  String searchValue;

  String payResult = PaymentResult.SUCCESS.getCode();

  String payKind;

  String startDt;
  String endDt;

  public long getPageStart() {
    init();
    return (pageIndex - 1) * pageSize;
  }

  public long getPageEnd() {
    init();
    return pageSize;
  }

  public void init(){
    if (pageIndex < 1) {
      pageIndex = 1;
    }

    if (pageSize < 10) {
      pageSize = 10;
    }
  }

  public String getQueryString() {
    init();

    StringBuilder sb = new StringBuilder();

    if (searchType != null && searchType.length() > 0) {
      sb.append(String.format("searchType=%s", searchType));
      if(searchType.equals("card")){
         payKind = "카드";
      }else if(searchType.equals("npay")){
         payKind = "네이버페이";
      }else if(searchType.equals("kpay")){
        payKind = "카카오페이";
      }else if(searchType.equals("phone")){
        payKind = "핸드폰결제";
      }
    }

    if (searchValue != null && searchValue.length() > 0) {
      if (sb.length() > 0) {
        sb.append("&");
      }
      sb.append(String.format("searchValue=%s", searchValue));
      searchValue = searchValue.trim();
      startDt = searchValue.substring(0, 8);
      endDt = searchValue.substring(9,17);
    }

    return sb.toString();
  }
}
