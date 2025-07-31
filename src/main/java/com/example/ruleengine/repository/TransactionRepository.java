package com.example.ruleengine.repository;

import com.example.ruleengine.entity.TrxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TrxEntity, Long> {
    List<TrxEntity> findByUniqueId(String uniqueId);
    @Query("SELECT COUNT(t) FROM TrxEntity t WHERE SUBSTRING(t.cardNumber, 1, 6) = :bin AND t.respCode <> '00' AND t.txndateYYYYMMDD = :today AND FUNCTION('TIME', t.txntimeHHMMSS) >= :startTime AND FUNCTION('TIME', t.txntimeHHMMSS) <= :endTime ")
    long countFailedTxnsForBin(
            @Param("bin") String bin,
            @Param("today") String today,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime
    );

}
