package com.bookshop.bookshopmanager.member.book.controller;

import static com.bookshop.bookshopmanager.common.util.JsonUtils.utf8Encording;

import com.bookshop.bookshopmanager.member.book.dto.MemberCartDto;
import com.bookshop.bookshopmanager.member.book.model.AddCartParam;
import com.bookshop.bookshopmanager.member.book.service.MemberCartBookService;
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
public class MemberCartBookController {
  //장바구니 컨트롤러

  private final MemberCartBookService memberCartBookService;

  @PostMapping("/cart.do")
  public String addCartBook(Model model, AddCartParam addCartParam, Principal principal){
    //장바구니 담기
    if(addCartParam.getBookCount() < addCartParam.getCartCount()){
      throw new RuntimeException("주문수량을 확인해주세요.");
    }

    var userId = principal.getName();

    //장바구니 데이터 저장
    memberCartBookService.addCartBook(addCartParam, userId);
//    model.addAttribute("result", "장바구니 담기에 성공했습니다.");

    return "redirect:/member/book/book.do?searchType="+addCartParam.getSearchType()+"&searchValue="+ utf8Encording(addCartParam.getSearchValue());
  }

  //장바구니 리스트
  @GetMapping("/cartlist.do")
  public String getCartList(Model model, Principal principal){

    var userId = principal.getName();
    List<MemberCartDto> list = memberCartBookService.getMemberCartList(userId);

    int totalCnt = 0;
    int totalPrice = 0;

    for(var item : list){
      totalCnt += item.getTotCount();
      totalPrice += item.getTotPrice();
    }

    model.addAttribute("list", list);
    model.addAttribute("totalcnt", totalCnt);
    model.addAttribute("totalprice", totalPrice);

    return "/member/cartlist";
  }

  //장바구니 삭제
  @PostMapping("/cartdelete.do")
  public String deleteCartBook(String isbn, Principal principal){

    var userId = principal.getName();

    memberCartBookService.delCartBook(userId, isbn);

    return "redirect:/member/cartlist.do";
  }
}
