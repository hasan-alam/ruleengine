package com.example.ruleengine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "rule")
@Data
public class RuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ruleId;
    @OneToMany(mappedBy = "ruleEntity")
    private List<RuleHitEntity> ruleHitEntityList;
    @NotEmpty
    @Length(max = 100)
    private String ruleTitle;
    @Length(max = 250)
    private String ruleDesc;
    @NotEmpty
    @Length(max =1500)
    @Column(columnDefinition = "TEXT")
    @Lob
    private String ruleJson;
    private boolean active;
    private String modifiedBy;
    private String lastUpdated;
    private String trxType; //ACQ, ISS, BOTH (in UI, make it Transaction Source)
    private String createdBy;
    private String creationDateTime;
    private String cardBrand;//Any,VISA, MCI, DCI,Amex, JCI
    private String cardType; //Any, Credit Card, Debit Card, Pre-Paid Card
    private String effectiveFromDate; //YYYYMMDD
    private String effectiveEndDate; //YYYYMMDD
    private String transactionChannel; //Any, QR,POS,ATM,NPSB, VISA, MCI, DCI
    private String priority;
}
