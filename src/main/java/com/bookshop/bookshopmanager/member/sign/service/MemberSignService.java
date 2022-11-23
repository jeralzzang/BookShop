package com.bookshop.bookshopmanager.member.sign.service;

import com.bookshop.bookshopmanager.admin.main.model.MemberParam;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberSignService extends UserDetailsService {
//회원가입 기능
  boolean memberSignUp(MemberParam parameter);
}
