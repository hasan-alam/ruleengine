package com.example.ruleengine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rule_hit")
@Data
public class RuleHitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "trxId")
    private TrxEntity trxEntity;
    @ManyToOne
    @JoinColumn(name = "ruleId")
    private RuleEntity ruleEntity;

}
