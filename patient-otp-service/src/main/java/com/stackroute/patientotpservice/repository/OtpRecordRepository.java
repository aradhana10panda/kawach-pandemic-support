package com.stackroute.patientotpservice.repository;

import com.stackroute.patientotpservice.model.OtpRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OtpRecordRepository extends JpaRepository<OtpRecord, Long> {

    Optional<OtpRecord> findTopByEmailAndUsedFalseOrderByCreatedAtDesc(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM OtpRecord o WHERE o.email = :email")
    void deleteByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM OtpRecord o WHERE o.expiresAt < :now")
    void deleteExpiredOtps(LocalDateTime now);
}
