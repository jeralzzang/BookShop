package com.bookshop.bookshopmanager.member.sign.service.impl;

import com.bookshop.bookshopmanager.member.sign.domain.Member;
import com.bookshop.bookshopmanager.member.sign.exception.MemberSignException;
import com.bookshop.bookshopmanager.admin.main.model.MemberParam;
import com.bookshop.bookshopmanager.member.sign.repository.MemberSignRepository;
import com.bookshop.bookshopmanager.member.sign.service.MemberSignService;
import com.bookshop.bookshopmanager.member.sign.type.MemberStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberSignServiceImpl implements MemberSignService {

  private final MemberSignRepository memberSignRepository;

  @Override
  public boolean memberSignUp(MemberParam parameter) {
    //Id가 중복일경우
    Optional<Member> memberOptional = memberSignRepository.findById(parameter.getId());
    if(memberOptional.isPresent()){
      return false;
    }
    //nickName이 중복일경우
    memberOptional = memberSignRepository.findByNickName(parameter.getNickName());
    if(memberOptional.isPresent()){
      return false;
    }

    //패스워드암호화
    String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

    Member member = Member.builder()
        .id(parameter.getId())
        .password(encPassword)
        .nickName(parameter.getNickName())
        .regDt(LocalDate.now())
        .udtDt(LocalDate.now())
        .status(MemberStatus.IN_USE)
        .adminYn(false)
        .build();

    memberSignRepository.save(member);

    return true;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Member> memberOptional = memberSignRepository.findById(username);
    if(!memberOptional.isPresent()){
      throw new MemberSignException("해당 유저가 없습니다.");
    }
    Member member = memberOptional.get();

    if(member.getStatus().equals(MemberStatus.ADMIN_DEL)){
      throw new MemberSignException("관리자에 의해 정지된 회원입니다.");
    }

    if(member.getStatus().equals(MemberStatus.USER_DEL)){
      throw new MemberSignException("탈퇴된 회원입니다.");
    }

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

    if (member.isAdminYn()) {
      grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    return new User(member.getId(), member.getPassword(), grantedAuthorities);
  }
}
