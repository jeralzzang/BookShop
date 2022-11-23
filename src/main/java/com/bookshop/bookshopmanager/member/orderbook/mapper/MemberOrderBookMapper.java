package com.bookshop.bookshopmanager.member.orderbook.mapper;

import com.bookshop.bookshopmanager.member.orderbook.dto.MemberOrderBookDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberOrderBookMapper {
  List<MemberOrderBookDto> getOrderBookList(int userNo, String status);
}
