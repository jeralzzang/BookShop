package com.bookshop.bookshopmanager.member.payment.controller;

import com.bookshop.bookshopmanager.member.payment.model.PaymentResult;
import com.bookshop.bookshopmanager.member.payment.service.MemberPaymentService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
//결제컨트롤러
public class MemberPaymentController {

  private final MemberPaymentService memberPaymentService;

  @PostMapping("/payment.do")
  public String memberPayment(Model model, String paykind, Principal principal){
    //결제정보 디비에 저장

    PaymentResult result = memberPaymentService.memberPaymentMain(principal.getName(), paykind);

    model.addAttribute("result", result.getMessage());

    return "/member/payment";
  }
}
