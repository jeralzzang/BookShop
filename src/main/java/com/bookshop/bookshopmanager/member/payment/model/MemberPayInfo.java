package com.bookshop.bookshopmanager.member.payment.model;

import lombok.Builder;

@Builder
public class MemberPayInfo {
    private int priceTot;
    private String cardInfo;
}
