package com.bookshop.bookshopmanager.admin.main.repository;

import com.bookshop.bookshopmanager.admin.main.entity.OrderBookEntity;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBookEntity, Integer> {
  //상태수정할 데이터가져오기
  Optional<OrderBookEntity> findByNo(int no);

  //주문도서 삭제
  void deleteByNo(int no);

}
