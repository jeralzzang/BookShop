package com.bookshop.bookshopmanager.member.book.controller;

import com.bookshop.bookshopmanager.admin.main.model.AddBookParam;
import com.bookshop.bookshopmanager.member.book.service.MemberBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/book/")
public class MemberBookController {
  private final MemberBookService memberBookService;

  //member 도서 조회
  @GetMapping("/book.do")
  public String getSearchBookList(Model model, AddBookParam parameter){
    //db에 있는 도서 조회

    if(parameter.getSearchType() != null){
      parameter.init();
      var bookList = memberBookService.getSearchBookInfo(parameter);

      String paramList = parameter.getQueryString();

      model.addAttribute("list", bookList);
    }

    return "/member/searchbook";
  }
}
