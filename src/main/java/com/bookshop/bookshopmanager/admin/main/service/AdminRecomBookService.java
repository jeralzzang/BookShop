package com.bookshop.bookshopmanager.admin.main.service;

import com.bookshop.bookshopmanager.admin.main.dto.RecomBookDto;
import com.bookshop.bookshopmanager.admin.main.entity.AddBookEntity;
import com.bookshop.bookshopmanager.admin.main.entity.RecomBookEntity;
import com.bookshop.bookshopmanager.admin.main.mapper.AdminRecomBookMapper;
import com.bookshop.bookshopmanager.admin.main.repository.AddBookRepository;
import com.bookshop.bookshopmanager.admin.main.repository.RecomBookRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminRecomBookService {

  private final AdminRecomBookMapper recomBookMapper;
  private final RecomBookRepository recomBookRepository;

  private final AddBookRepository addBookRepository;

  //추천도서 리스트조회
  public List<RecomBookDto> getRecomBookList(){
    return recomBookMapper.getRecomBookList();
  }


  //도서테이블에서 추천컬럼 변경
  private void setRecomBook(String isbn, boolean recomYn){
    AddBookEntity book = addBookRepository.findByIsbn(isbn);
    book.setRecomYn(recomYn);
    addBookRepository.save(book);
  }
  //추천도서 등록
  public void addRecomBook(String isbn){
    setRecomBook(isbn, true);
    RecomBookEntity book = RecomBookEntity.builder()
        .isbn(isbn)
        .regDt(LocalDate.now())
        .build();
    recomBookRepository.save(book);
  }

  //추천도서 상세
  public RecomBookDto getRecomBook(String isbn) {
    return recomBookMapper.getRecomBook(isbn);
  }

  //추천도서 삭제
  public void deleteRecomBook(String isbn){
    setRecomBook(isbn, false);
    recomBookRepository.deleteByIsbn(isbn);
  }

  public void updateRecomBook(String isbn, String content) {
    RecomBookEntity book = recomBookRepository.findByIsbn(isbn);
    book.setContent(content);
    recomBookRepository.save(book);
  }
}
