package com.bookshop.bookshopmanager.member.orderbook.service;

import com.bookshop.bookshopmanager.admin.main.entity.OrderBookEntity;
import com.bookshop.bookshopmanager.admin.main.mapper.AdminMainMemberMapper;
import com.bookshop.bookshopmanager.admin.main.repository.OrderBookRepository;
import com.bookshop.bookshopmanager.member.orderbook.dto.MemberOrderBookDto;
import com.bookshop.bookshopmanager.member.orderbook.mapper.MemberOrderBookMapper;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberOrderBookService {
  private final AdminMainMemberMapper adminMainMemberMapper;
  private final OrderBookRepository orderBookRepository;
  private final MemberOrderBookMapper memberOrderBookMapper;

  //주문도서 등록
  public Boolean addOrderBook(String isbn, int count, String userId){
    boolean result = false;
    //사용자키 가져오기
    int userNo = adminMainMemberMapper.getMemberUserNo(userId);

    orderBookRepository.save(OrderBookEntity.builder()
            .isbn(isbn)
            .count(count)
            .orderDate(LocalDate.now())
            .status("신청")
            .userNo(userNo)
        .build());
    result = true;

    return result;
  }

  //나의 주문도서 조회
  public List<MemberOrderBookDto> getMemberMyOrderBookList(String status, String userId) {
    int userNo = adminMainMemberMapper.getMemberUserNo(userId);

    return memberOrderBookMapper.getOrderBookList(userNo, status);
  }

  //나의 주문도서 삭제
  public void delMemberMyOrderBook(int no){
    orderBookRepository.deleteByNo(no);
  }
}
