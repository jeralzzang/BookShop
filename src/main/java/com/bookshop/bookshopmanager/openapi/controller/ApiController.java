package com.bookshop.bookshopmanager.openapi.controller;

import com.bookshop.bookshopmanager.openapi.service.ApiService;
import com.bookshop.bookshopmanager.openapi.model.RequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addbook")
@RequiredArgsConstructor
public class ApiController {
  private final ApiService apiService;

  @GetMapping()
  public ResponseEntity<?> getOpenApiBookInfo(RequestParam parameter){
    String json = apiService.getResponseJsonData(parameter);

    return ResponseEntity.ok(json);
  }
}
