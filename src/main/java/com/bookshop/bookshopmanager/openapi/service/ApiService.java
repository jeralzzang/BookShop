package com.bookshop.bookshopmanager.openapi.service;

import com.bookshop.bookshopmanager.openapi.model.RequestParam;
import com.bookshop.bookshopmanager.openapi.model.ResponseParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

  public String getResponseJsonData(RequestParam parameter){
    parameter.setSearchValue(URLDecoder.decode(parameter.getSearchValue(), StandardCharsets.UTF_8));
    List<ResponseParam> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
        ResponseParam object = ResponseParam.builder()
            .isbn(String.valueOf(i))
            .title("도서".concat(String.valueOf(i)))
            .writer("작가".concat(String.valueOf(i)))
            .publisher("출판사".concat(String.valueOf(i)))
            .price(15000)
            .imgUrl("URL".concat(String.valueOf(i)))
            .build();
        list.add(object);
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    return gson.toJson(list);
  }

}
