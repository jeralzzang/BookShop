package com.bookshop.bookshopmanager.admin.main.mapper;

import com.bookshop.bookshopmanager.admin.main.dto.OrderBookDto;
import com.bookshop.bookshopmanager.admin.main.model.OrderBookParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminOrderBookMapper {
  long getOrderBookListCount(OrderBookParam parameter);
  List<OrderBookDto> getOrderBookList(OrderBookParam parameter);
}
