package com.bookshop.bookshopmanager.member.main.controller;

import com.bookshop.bookshopmanager.admin.main.dto.RecomBookDto;
import com.bookshop.bookshopmanager.admin.main.service.AdminRecomBookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberMaincontroller {

  private final AdminRecomBookService adminRecomBookService;

  @RequestMapping("/")
  public String mainPage(Model model){
    List<RecomBookDto> recomBookDtos = adminRecomBookService.getRecomBookList();

    model.addAttribute("list", recomBookDtos);

    return "/index";
  }
}
