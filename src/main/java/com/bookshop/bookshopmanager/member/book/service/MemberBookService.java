package com.bookshop.bookshopmanager.member.book.service;

import com.bookshop.bookshopmanager.admin.main.model.AddBookParam;
import com.bookshop.bookshopmanager.member.book.dto.MemberBookDto;
import com.bookshop.bookshopmanager.member.book.mapper.MemberBookMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberBookService {

  private final MemberBookMapper memberBookMapper;

  //도서 검색
  public List<MemberBookDto> getSearchBookInfo(AddBookParam parameter) {
    return memberBookMapper.getMemberBookList(parameter);
  }
}
