package com.bookshop.bookshopmanager.member.book.service;

import com.bookshop.bookshopmanager.member.book.dto.MemberBookDto;
import com.bookshop.bookshopmanager.member.book.dto.MemberCartDto;
import com.bookshop.bookshopmanager.member.book.mapper.MemberBookMapper;
import com.bookshop.bookshopmanager.member.book.model.AddCartParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCartBookService {
  private final MemberBookMapper memberBookMapper;
  private final RedisTemplate<String, String> redisTemplate;

  //장바구니 데이터 저장
  public void addCartBook(AddCartParam parameter, String userId){
    MemberBookDto memberBookDto = memberBookMapper.getMemberBook(parameter.getIsbn());
    MemberCartDto memberCartDto = new MemberCartDto(memberBookDto);

    memberCartDto.setTotCount(parameter.getCartCount());
    memberCartDto.setTotPrice(parameter.getCartCount()* memberBookDto.getPrice());

    //redis에 저장 key=userId, value = memberCartDto
    addCartDataToRedis(userId, memberCartDto);
  }
  //장바구니 데이터 일괄 삭제
  public void dropCartBook(String userId){
    redisTemplate.delete(userId);
  }

  //장바구니 데이터 삭제
  public void delCartBook(String userId, String isbn){
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    List<MemberCartDto> list = new ArrayList<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String items = valueOperations.get(userId);
    MemberCartDto[] object = gson.fromJson(items, MemberCartDto[].class);
    for(var item : object) {
      if (!item.getIsbn().equals(isbn)) {
        list.add(item);
      }
    }
    valueOperations.set(userId, gson.toJson(list));
  }

  //장바구니 데이터 조회
  public List<MemberCartDto> getMemberCartList(String userId){
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    List<MemberCartDto> list = new ArrayList<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    기존에 데이터 있는지 체크
    if(valueOperations.size(userId)>0){
      //장바구니 데이터 가져오기
      String items = valueOperations.get(userId);
      MemberCartDto[] object = gson.fromJson(items, MemberCartDto[].class);
      for(var item : object){
        list.add(item);
      }
    }

    return list;
  }

  private void addCartDataToRedis(String key, MemberCartDto memberCartDto){
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    List<MemberCartDto> list = new ArrayList<>();
//    기존에 데이터 있는지 체크
    if(valueOperations.size(key)>0){
      //기존 데이터 존재시 추가 작업
      String items = valueOperations.get(key);
      MemberCartDto[] object = gson.fromJson(items, MemberCartDto[].class);
      for(var item : object){
        list.add(item);
      }
    }
    list.add(memberCartDto);

    valueOperations.set(key, gson.toJson(list));
  }
}
