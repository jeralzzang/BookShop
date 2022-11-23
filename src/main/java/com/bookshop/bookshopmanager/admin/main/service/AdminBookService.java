package com.bookshop.bookshopmanager.admin.main.service;

import com.bookshop.bookshopmanager.admin.main.dto.BookInfoDto;
import com.bookshop.bookshopmanager.admin.main.entity.AddBookEntity;
import com.bookshop.bookshopmanager.admin.main.model.AddBookParam;
import com.bookshop.bookshopmanager.admin.main.repository.AddBookRepository;
import com.bookshop.bookshopmanager.common.util.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminBookService {
  private final AddBookRepository addBookRepository;
  private final String BOOK_API_URL = "http://localhost:8080/addbook?searchType=%s&searchValue=%s";

  public List<AddBookEntity> getSearchBookInfo(AddBookParam parameter){
  /*
    openapi에서 도서정보 가져오기
    도서정보 db저장
    도서정보 조회
   */
   List<BookInfoDto> bookInfoDtos = getBookInfoToApi(parameter);

   for(var item : bookInfoDtos){
     if(!addBookRepository.existsByIsbn(item.getIsbn())){
       item.setCount(0);
       addBookRepository.save(new AddBookEntity(item));
     }
   }

   List<AddBookEntity> list;

   if(parameter.getSearchType().equals("title")){
      list = addBookRepository.findByTitleLike("%" + parameter.getSearchValue() + "%");
   }else{
     list = addBookRepository.findByWriterLike("%" + parameter.getSearchValue() + "%");
   }

   return list;
  }

  //주문대상 도서 조회
  public List<AddBookEntity> getOrdeBookInfo(AddBookParam parameter){
  /*
    openapi에서 도서정보 가져오기
    도서정보 db저장
    도서정보 조회
   */
    List<BookInfoDto> bookInfoDtos = getBookInfoToApi(parameter);

    for(var item : bookInfoDtos){
      if(!addBookRepository.existsByIsbn(item.getIsbn())){
        item.setCount(0);
        addBookRepository.save(new AddBookEntity(item));
      }
    }

    List<AddBookEntity> list;

    if(parameter.getSearchType().equals("title")){
      list = addBookRepository.findByTitleLikeAndCountEquals("%" + parameter.getSearchValue() + "%", 0);
    }else{
      list = addBookRepository.findByWriterLikeAndCountEquals("%" + parameter.getSearchValue() + "%", 0);
    }

    return list;
  }

  protected List<BookInfoDto> getBookInfoToApi(AddBookParam parameter){
    String urlString = String.format(BOOK_API_URL, parameter.getSearchType(), URLEncoder.encode(parameter.getSearchValue(),
        StandardCharsets.UTF_8));
    JsonUtils jsonUtils = new JsonUtils();
    String json = jsonUtils.getJsonToApi(urlString);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    BookInfoDto[] object = gson.fromJson(json, BookInfoDto[].class);

    return Arrays.asList(object);
  }

}
