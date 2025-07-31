package com.example.ruleengine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rule_action")
public class RuleActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "trxId")
    private TrxEntity trxEntity;
    @ManyToOne
    @JoinColumn(name = "ruleId")
    private RuleEntity ruleEntity;
    private String action;
}
