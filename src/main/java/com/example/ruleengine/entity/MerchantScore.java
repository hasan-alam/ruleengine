package com.example.ruleengine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MerchantScore {
    @Id
    private String merchantId;
    private int score;
}
