package com.bookshop.bookshopmanager.admin.main.controller;

import com.bookshop.bookshopmanager.admin.main.dto.MemberDto;
import com.bookshop.bookshopmanager.admin.main.dto.OrderBookDto;
import com.bookshop.bookshopmanager.admin.main.model.OrderBookParam;
import com.bookshop.bookshopmanager.admin.main.model.OrderBookStatusInput;
import com.bookshop.bookshopmanager.admin.main.service.AdminMainService;
import com.bookshop.bookshopmanager.common.util.Pager;
import com.bookshop.bookshopmanager.admin.main.model.MemberParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMainController {

  private final AdminMainService adminMainService;
  //관리자 메인화면
  @GetMapping("/main.do")
  public String main(){
    return "/admin/main";
  }

  //회원관리
  @GetMapping("/memberList.do")
  public String memberList(Model model, MemberParam parameter) {

    parameter.init();
    List<MemberDto> memberList = adminMainService.getMemberList(parameter);
    long totalCount = adminMainService.getmemberCount(parameter);
    String paramList = parameter.getQueryString();
    String pagerHtml = new Pager().getPaperHtml(totalCount, parameter.getPageSize(),
        parameter.getPageIndex(), paramList);

    model.addAttribute("list", memberList);
    model.addAttribute("totalCount", totalCount);
    model.addAttribute("pager", pagerHtml);

    return "/admin/memberlist";
  }

  //주문 도서리스트
  @GetMapping("/orderbook.do")
  public String memberBookOrder(Model model, OrderBookParam parameter){

    if(parameter.getSearchType()==null){
      parameter.setSearchType("status");
      parameter.setSearchValue("신청");
    }
    parameter.init();

    List<OrderBookDto> list = adminMainService.getOrderBookList(parameter);
    long totalCount = adminMainService.getOrderBookCount(parameter);
    String paramList = parameter.getQueryString();
    String pagerHtml = new Pager().getPaperHtml(totalCount, parameter.getPageSize(),
        parameter.getPageIndex(), paramList);
    model.addAttribute("list", list);
    model.addAttribute("totalCount", totalCount);
    model.addAttribute("pager", pagerHtml);

    return "/admin/orderbook";
  }
  //주문도시 상태값 변경
  @PostMapping("/orderbook/status.do")
  public String orderBookStatusUpdate(OrderBookStatusInput parameter){
    if(ObjectUtils.isEmpty(parameter)){
      throw new RuntimeException("Call Error");
    }
    adminMainService.updateOrderBookStatus(parameter);

    return "redirect:/admin/orderbook.do";
  }

}
