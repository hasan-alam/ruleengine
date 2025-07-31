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
    private String ruleJson;
    private boolean active;
    private String modifiedBy;
    private String lastUpdated;
    private String trxType; //ACQ, ISS, BOTH
}
