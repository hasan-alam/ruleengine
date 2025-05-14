package com.example.ruleengine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ruleId;
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
}
