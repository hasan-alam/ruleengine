package com.example.ruleengine.repository;

import com.example.ruleengine.entity.RuleHitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleHitRepository extends JpaRepository<RuleHitEntity,Long> {
}
