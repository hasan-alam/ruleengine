package com.example.ruleengine.repository;

import com.example.ruleengine.entity.CardScore;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CardScoreRepository extends JpaRepository<CardScore,String> {
}
