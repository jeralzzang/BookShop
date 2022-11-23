package com.bookshop.bookshopmanager.member.payment.service;

import com.bookshop.bookshopmanager.admin.main.mapper.AdminMainMemberMapper;
import com.bookshop.bookshopmanager.admin.main.repository.AddBookRepository;
import com.bookshop.bookshopmanager.member.book.dto.MemberCartDto;
import com.bookshop.bookshopmanager.member.book.service.MemberCartBookService;
import com.bookshop.bookshopmanager.member.payment.entity.MemberPaymentDetailEntity;
import com.bookshop.bookshopmanager.member.payment.entity.MemberPaymentMasterEntity;
import com.bookshop.bookshopmanager.member.payment.mapper.MemberPaymentMapper;
import com.bookshop.bookshopmanager.member.payment.model.MemberPayInfo;
import com.bookshop.bookshopmanager.member.payment.model.PaymentKeyParam;
import com.bookshop.bookshopmanager.member.payment.model.PaymentResult;
import com.bookshop.bookshopmanager.member.payment.repository.MemberPaymentDetailRepository;
import com.bookshop.bookshopmanager.member.payment.repository.MemberPaymentMasterRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberPaymentService {

  private final MemberCartBookService memberCartBookService;
  private final MemberPaymentMapper memberPaymentMapper;
  private final AdminMainMemberMapper adminMainMemberMapper;
  private final MemberPaymentMasterRepository memberPaymentMasterRepository;
  private final MemberPaymentDetailRepository memberPaymentDetailRepository;
  private final AddBookRepository addBookRepository;

  //결제전 결제정보 디비에 저장
  public PaymentResult memberPaymentMain(String userId, String payKind){
    //장바구니 데이터 list로 가져오기
    List<MemberCartDto> books = getCartBookList(userId);
    //결제정보 마스터테이블 저장
    PaymentKeyParam payMentKeys = addPaymentMaster(books, payKind, userId);

    //결제정보 디테일저장
    addPaymentDetail(books, payMentKeys);
    //결제결과 저장
    MemberPaymentMasterEntity paymentInfo =
        memberPaymentMasterRepository.findByPayDtAndPayNo(payMentKeys.getPayDt(),
            payMentKeys.getPayNo());

    //결제 요청 후 결과값 반환
    PaymentResult paymentResult = paymentApiCall(paymentInfo.getPayInfo(), paymentInfo.getPriceTot());

    paymentInfo.setResult(paymentResult.getMessage());
    paymentInfo.setResultCode(paymentResult.getCode());
    memberPaymentMasterRepository.save(paymentInfo);

    if(Objects.equals(paymentResult.getMessage(), PaymentResult.SUCCESS.getMessage())) {
      //결제 성공시 redis에 있는 데이터 삭제
      memberCartBookService.dropCartBook(userId);

      //재고 수정
      for(var item : books){
        var book = addBookRepository.findByIsbn(item.getIsbn());
        book.setCount(book.getCount() -item.getTotCount());
        addBookRepository.save(book);
      }
    }

    return paymentResult;
  }

  //결제요청 api호출
  private PaymentResult paymentApiCall(String payInfo, int price) {
    if(price > 100000){
      return PaymentResult.FAIL;
    }else{
      return PaymentResult.SUCCESS;
    }
  }

  //결제정보 디테일 저장
  private void addPaymentDetail(List<MemberCartDto> books, PaymentKeyParam paymentKeys) {
    int paySeq = 1;
    LocalDate now = LocalDate.now();
    for(var item : books){
      memberPaymentDetailRepository.save(MemberPaymentDetailEntity.builder()
              .payDt(paymentKeys.getPayDt())
              .payNo(paymentKeys.getPayNo())
              .paySeq(paySeq++)
              .isbn(item.getIsbn())
              .price(item.getTotPrice())
              .count(item.getTotCount())
              .regDt(now)
          .build());
    }
  }

  //장바구니 데이터 list로 가져오기
  private List<MemberCartDto> getCartBookList(String key){
    return memberCartBookService.getMemberCartList(key);
  }

  //결제정보마스터 테이블 저장
  private PaymentKeyParam addPaymentMaster(List<MemberCartDto> books, String payKind, String userId){
    String payDt = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));
    int payNo = memberPaymentMapper.getPaymentKey(payDt);

    int totalPrice =0;
    for(var item : books){
      totalPrice += item.getTotPrice();
    }
//유저id로 유저no 가져오기

    int userNo = adminMainMemberMapper.getMemberUserNo(userId);

    //결제요청 정보 만들기
    String requestPayInfo = buildRequestPayInfo(MemberPayInfo.builder()
        .priceTot(totalPrice)
        .cardInfo("")
        .build());

    MemberPaymentMasterEntity paymentMaster = MemberPaymentMasterEntity.builder()
        .payDt(payDt)
        .payNo(payNo)
        .priceTot(totalPrice)
        .payKind(payKind)
        .userNo(userNo)
        .regDt(LocalDate.now())
        .payInfo(requestPayInfo)
    .build();

    memberPaymentMasterRepository.save(paymentMaster);

    return PaymentKeyParam.builder()
        .payDt(payDt)
        .payNo(payNo)
        .build();
  }

  //요청 결제정보 만들기
  private String buildRequestPayInfo(MemberPayInfo parameter){

    return parameter.toString();
  }
}
