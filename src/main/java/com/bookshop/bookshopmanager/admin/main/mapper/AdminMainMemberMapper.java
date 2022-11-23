package com.bookshop.bookshopmanager.admin.main.mapper;

import com.bookshop.bookshopmanager.admin.main.dto.MemberDto;
import com.bookshop.bookshopmanager.admin.main.model.MemberParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMainMemberMapper {
  long getMemberListCount(MemberParam parameter);
  List<MemberDto> getMemberList(MemberParam parameter);
  int getMemberUserNo(String userId);
}
