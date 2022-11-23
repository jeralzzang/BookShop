package com.bookshop.bookshopmanager.member.sign.repository;

import com.bookshop.bookshopmanager.member.sign.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSignRepository extends JpaRepository<Member, Integer> {
  //ID중복인지 확인
  Optional<Member> findById(String id);
  Optional<Member> findByNo(int no);

  //닉네임이 중복인지 확인
  Optional<Member> findByNickName(String nickName);
}
