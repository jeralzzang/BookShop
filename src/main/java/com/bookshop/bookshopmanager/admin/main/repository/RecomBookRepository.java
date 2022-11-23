package com.bookshop.bookshopmanager.admin.main.repository;

import com.bookshop.bookshopmanager.admin.main.entity.RecomBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecomBookRepository extends JpaRepository<RecomBookEntity, String> {

  @Transactional
  void deleteByIsbn(String isbn);
  RecomBookEntity findByIsbn(String isbn);
}
