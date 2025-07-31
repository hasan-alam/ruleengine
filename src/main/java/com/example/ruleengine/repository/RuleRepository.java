package com.example.ruleengine.repository;

import com.example.ruleengine.entity.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, String> {
    List<RuleEntity> findByActiveTrue();
}
