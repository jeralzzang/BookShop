package com.bookshop.bookshopmanager.member.orderbook.controller;

import com.bookshop.bookshopmanager.admin.main.model.AddBookParam;
import com.bookshop.bookshopmanager.admin.main.service.AdminBookService;
import com.bookshop.bookshopmanager.member.orderbook.dto.MemberOrderBookDto;
import com.bookshop.bookshopmanager.member.orderbook.service.MemberOrderBookService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberOrderBookController {

  private final AdminBookService adminBookService;
  private final MemberOrderBookService memberOrderBookService;
//주문도서 관리
  @GetMapping("/orderbook.do")
  public String getMemberOrderBookList(Model model, AddBookParam parameter){
    //1.도서 api 조회
    //2.조회된 정보 db저장
    //3.db에서 재고 없는 도서 정보
    if(parameter.getSearchType() != null){
      parameter.init();
      var bookList = adminBookService.getOrdeBookInfo(parameter);

      String paramList = parameter.getQueryString();

      model.addAttribute("list", bookList);
    }

    return "/member/orderbook";
  }

  @PostMapping("/orderbook.do")
  public String addMemberOrderBook(Model model, String isbn, int orderCount, Principal principal){

    if(memberOrderBookService.addOrderBook(isbn, orderCount, principal.getName())){
      model.addAttribute("result", "주문신청 성공");
    }else{
      model.addAttribute("result", "주문신청 실패");
    }

    return "/member/orderresult";
  }

  //내 주문도서 조회
  @GetMapping("/myorderbook.do")
  public String getOrderBookList(Model model, String status, Principal principal){

    List<MemberOrderBookDto> list = memberOrderBookService.getMemberMyOrderBookList(status, principal.getName());

    model.addAttribute("list", list);

    return "/member/myorderbook";
  }

  @PostMapping("/myorderbookdelete.do")
  public String myorderbookcancel(Model model, String no){

    memberOrderBookService.delMemberMyOrderBook(Integer.parseInt(no));

    model.addAttribute("result", "삭제되었습니다.");
    return "/member/myorderbookdelete";
  }

}
