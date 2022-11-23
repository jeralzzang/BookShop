package com.bookshop.bookshopmanager.member.payment.repository;

import com.bookshop.bookshopmanager.member.payment.entity.MemberPaymentDetailEntity;
import com.bookshop.bookshopmanager.member.payment.model.MemberPaymentDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPaymentDetailRepository extends JpaRepository<MemberPaymentDetailEntity, MemberPaymentDetailId> {

}
