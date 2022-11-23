package com.bookshop.bookshopmanager.member.book.mapper;

import com.bookshop.bookshopmanager.admin.main.model.AddBookParam;
import com.bookshop.bookshopmanager.member.book.dto.MemberBookDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberBookMapper {
  List<MemberBookDto> getMemberBookList(AddBookParam parameter);
  MemberBookDto getMemberBook(String isbn);
}
