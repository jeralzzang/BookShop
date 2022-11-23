package com.bookshop.bookshopmanager.admin.main.mapper;

import com.bookshop.bookshopmanager.admin.main.dto.RecomBookDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRecomBookMapper {
  List<RecomBookDto> getRecomBookList();
  long getRecomBookListCount();
  RecomBookDto getRecomBook(String isbn);
}
