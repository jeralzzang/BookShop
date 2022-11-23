package com.bookshop.bookshopmanager.admin.payment.controller;

import com.bookshop.bookshopmanager.admin.payment.dto.AdminPaymentDetailDto;
import com.bookshop.bookshopmanager.admin.payment.dto.AdminPaymentMasterDto;
import com.bookshop.bookshopmanager.admin.payment.model.PaymentDetailParam;
import com.bookshop.bookshopmanager.admin.payment.model.PaymentMasterParam;
import com.bookshop.bookshopmanager.admin.payment.service.AdminPaymentService;
import com.bookshop.bookshopmanager.common.util.Pager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminPaymentController {
  private final AdminPaymentService adminPaymentService;
//관리자 매출관리

  @GetMapping("/payment.do")
  public String getPaymentList(Model model, PaymentMasterParam parameter){
    parameter.init();

    List<AdminPaymentMasterDto> list = adminPaymentService.getpaymentMasterList(parameter);
    for (var item : list){
      adminPaymentService.setPaykind(item);
    }
    long totalCount = adminPaymentService.getPaymentCount(parameter);
    String paramList = parameter.getQueryString();
    String pagerHtml = new Pager().getPaperHtml(totalCount, parameter.getPageSize(),
        parameter.getPageIndex(), paramList);

    model.addAttribute("list", list);
    model.addAttribute("totalCount", totalCount);
    model.addAttribute("pager", pagerHtml);

    return "/admin/payment";
  }

  @PostMapping("paymentdetail.do")
  public String getPaymentDetail(Model model, PaymentDetailParam parameter){
    List<AdminPaymentDetailDto> list = adminPaymentService.getPaymentDetailList(parameter);


    model.addAttribute("list", list);

    return "/admin/paymentdetail";
  }
}
