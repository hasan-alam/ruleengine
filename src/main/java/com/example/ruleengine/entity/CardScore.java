package com.example.ruleengine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CardScore {
    @Id
    private String cardNumber;
    private int score;
}
