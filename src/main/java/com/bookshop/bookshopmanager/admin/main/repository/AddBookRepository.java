package com.bookshop.bookshopmanager.admin.main.repository;

import com.bookshop.bookshopmanager.admin.main.dto.BookInfoDto;
import com.bookshop.bookshopmanager.admin.main.entity.AddBookEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddBookRepository extends JpaRepository<AddBookEntity, String> {
  boolean existsByIsbn(String isbn);
  List<AddBookEntity> findByTitleLike(String title);
  List<AddBookEntity> findByWriterLike(String writer);
  long countByTitleLike(String title);
  long countByWriterLike(String writer);

  AddBookEntity findByIsbn(String isbn);
  List<AddBookEntity> findByTitleLikeAndCountEquals(String title, int count);
  List<AddBookEntity> findByWriterLikeAndCountEquals(String writer, int count);
}
