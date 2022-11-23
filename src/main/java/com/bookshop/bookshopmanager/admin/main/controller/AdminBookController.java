package com.bookshop.bookshopmanager.admin.main.controller;

import static com.bookshop.bookshopmanager.common.util.JsonUtils.utf8Encording;

import com.bookshop.bookshopmanager.admin.main.dto.RecomBookDto;
import com.bookshop.bookshopmanager.admin.main.entity.AddBookEntity;
import com.bookshop.bookshopmanager.admin.main.model.AddBookParam;
import com.bookshop.bookshopmanager.admin.main.repository.AddBookRepository;
import com.bookshop.bookshopmanager.admin.main.service.AdminBookService;
import com.bookshop.bookshopmanager.admin.main.service.AdminRecomBookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminBookController {
  //도서관리 컨트롤러

  private final AdminBookService adminBookService;
  private final AddBookRepository addBookRepository;
  private final AdminRecomBookService adminRecomBookService;

  //책제목으로 책조회
  @GetMapping("/book.do")
  public String searchBookList(Model model, AddBookParam parameter){
    //1.도서 api 조회
    //2.조회된 정보 db저장
    //3.db에서 도서 정보 가져오기
    if(parameter.getSearchType() != null){
      parameter.init();
      var bookList = adminBookService.getSearchBookInfo(parameter);

      String paramList = parameter.getQueryString();

      model.addAttribute("list", bookList);
    }

    return "/admin/searchbook";
  }

  //도서 재고수정
  @PostMapping("/book.do")
  public String updateBookCount(String bookcount, String isbn, String searchValue, String searchType){
    AddBookEntity book = addBookRepository.findByIsbn(isbn);
    book.setCount(Integer.valueOf(bookcount));
    addBookRepository.save(book);

    return "redirect:/admin/book.do?searchType="+searchType+"&searchValue="+ utf8Encording(searchValue);
  }

  //추천도서 등록
  @PostMapping("/addrecombook.do")
  public String addRecomBook(String isbn, String searchValue, String searchType){
    adminRecomBookService.addRecomBook(isbn);

    return "redirect:/admin/book.do?searchType="+searchType+"&searchValue="+ utf8Encording(searchValue);
  }

  //추천도서 수정
  @GetMapping("recombookdetail.do")
  public String detailRecomBook(Model model, String isbn){
    RecomBookDto recomBookDto = adminRecomBookService.getRecomBook(isbn);

    model.addAttribute("book", recomBookDto);
    return "/admin/recombookdetail";
  }

  //추천도서 조회
  @GetMapping("/recommend.do")
  public String getRecomBookList(Model model){
    //추천도서 리스트가져오기
    List<RecomBookDto> recomBookDtos = adminRecomBookService.getRecomBookList();

    model.addAttribute("list", recomBookDtos);
    return "/admin/recombook";
  }

  //추천도서 삭제
  @PostMapping("/recombookdel.do")
  public String deleteRecomStatus(String isbn){
    adminRecomBookService.deleteRecomBook(isbn);

    return "redirect:/admin/recommend.do";
  }

  //추천도서 수정
  @PostMapping("/recombookupd.do")
  public String updateRecomStatus(String isbn, String content){

    adminRecomBookService.updateRecomBook(isbn, content);

    return "redirect:/admin/recommend.do";
  }
}
