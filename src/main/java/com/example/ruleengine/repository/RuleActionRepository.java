package com.example.ruleengine.repository;

import com.example.ruleengine.entity.RuleActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleActionRepository extends JpaRepository<RuleActionEntity,Integer> {
}
