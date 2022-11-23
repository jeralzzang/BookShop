package com.bookshop.bookshopmanager.member.payment.repository;

import com.bookshop.bookshopmanager.member.payment.entity.MemberPaymentMasterEntity;
import com.bookshop.bookshopmanager.member.payment.model.MemberPaymentMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPaymentMasterRepository extends JpaRepository<MemberPaymentMasterEntity, MemberPaymentMasterId> {
    MemberPaymentMasterEntity findByPayDtAndPayNo(String payDt, int payNo);
}
