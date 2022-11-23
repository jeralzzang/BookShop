package com.bookshop.bookshopmanager.member.sign.controller;

import com.bookshop.bookshopmanager.admin.main.model.MemberParam;
import com.bookshop.bookshopmanager.member.sign.domain.Member;
import com.bookshop.bookshopmanager.member.sign.domain.MemberModifyParam;
import com.bookshop.bookshopmanager.member.sign.repository.MemberSignRepository;
import com.bookshop.bookshopmanager.member.sign.service.MemberSignService;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberSignController {

  private final MemberSignService memberSignService;
  private final MemberSignRepository memberSignRepository;

  //회원정보 수정
  @GetMapping("/membermod.do")
  public String memberModify(Model model, Principal principal){
    //회원정보 가져오기
    Optional<Member> memberOptional = memberSignRepository.findById(principal.getName());

    var member = memberOptional.get();
    model.addAttribute("member", member);

    return "/member/sign/signmod";
  }

  @PostMapping("/membermod.do")
  public String memberModifySubmit(Model model, MemberModifyParam parameter){

    //회원정보 가져와서 수정 후 저장
    if(!parameter.getPassword().equals("")){
      if(!parameter.getPassword().equals(parameter.getRePassword())){
        throw new RuntimeException("비밀번호와 확인비밀번호가 일치하지 않습니다.");
      }
    }
    Optional<Member> memberOptional = memberSignRepository.findByNo(parameter.getNo());
    var member = memberOptional.get();

    member.setNickName(parameter.getNickName());
    if(!parameter.getPassword().equals("")){
      member.setPassword(BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt()));
    }
    member.setUdtDt(LocalDate.now());

    memberSignRepository.save(member);

    model.addAttribute("result", "수정되었습니다.");
    return "/member/orderresult";
  }

  //회원가입화면
  @GetMapping("/signup.do")
  public String memberSignUp(){
    return "member/sign/signup";
  }

  //회원가입 정보 입력 후 회원가입실행
  @PostMapping("/signup.do")
  public String memberSignUpSubmit(Model model, HttpServletRequest request
      , MemberParam parameter){
    boolean result = memberSignService.memberSignUp(parameter);

    model.addAttribute("result", result);
    return "member/sign/signup_complete";
  }

  //로그인
  @RequestMapping("/signin.do")
  public String memberSignIn(){
    return "member/sign/signin";
  }

}
