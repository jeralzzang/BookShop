package com.bookshop.bookshopmanager.admin.main.service;

import com.bookshop.bookshopmanager.admin.main.dto.MemberDto;
import com.bookshop.bookshopmanager.admin.main.dto.OrderBookDto;
import com.bookshop.bookshopmanager.admin.main.entity.OrderBookEntity;
import com.bookshop.bookshopmanager.admin.main.mapper.AdminMainMemberMapper;
import com.bookshop.bookshopmanager.admin.main.mapper.AdminOrderBookMapper;
import com.bookshop.bookshopmanager.admin.main.model.MemberParam;
import com.bookshop.bookshopmanager.admin.main.model.OrderBookParam;
import com.bookshop.bookshopmanager.admin.main.model.OrderBookStatusInput;
import com.bookshop.bookshopmanager.admin.main.repository.OrderBookRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMainService {

  private final AdminMainMemberMapper adminMainMemberMapper;
  private final AdminOrderBookMapper adminOrderbookMapper;
  private final OrderBookRepository orderBookRepository;
  //memberList 조회
  public List<MemberDto> getMemberList(MemberParam parameter) {
    return adminMainMemberMapper.getMemberList(parameter);
  }

  //조건에 맞는 멤버 총 건수 조회
  public long getmemberCount(MemberParam parameter){
    return adminMainMemberMapper.getMemberListCount(parameter);
  }

  //주문도서 총건수 조회

  public List<OrderBookDto> getOrderBookList(OrderBookParam parameter){
    return adminOrderbookMapper.getOrderBookList(parameter);
  }

  //주문도서 리스트 조회
  public Long getOrderBookCount(OrderBookParam parameter){
    return adminOrderbookMapper.getOrderBookListCount(parameter);
  }

  //주문도서 상태 업데이트
  public boolean updateOrderBookStatus(OrderBookStatusInput parameter){
    Optional<OrderBookEntity> orderBookEntityOptional =
        orderBookRepository.findByNo(parameter.getNo());

    if(orderBookEntityOptional.isEmpty()){
      throw new RuntimeException("orderBook No not found -> " + parameter.getNo());
    }

    var orderBookEntity = orderBookEntityOptional.get();
    String status;
    if(parameter.getOrderStatus().equals("REQ")){
        status = "신청";
    }else if(parameter.getOrderStatus().equals("ACC")){
      status = "수락";
    }else if(parameter.getOrderStatus().equals("REF")){
      status = "거절";
    }else if(parameter.getOrderStatus().equals("ING")){
      status = "주문중";
    }else{
      status = "판매완료";
    }
    orderBookEntity.setStatus(status);
    orderBookRepository.save(orderBookEntity);

    return true;
  }
}