package com.example.ruleengine.model;

import lombok.Data;

@Data
public class Purchase {
    private String cardNumber;
    private double cardLimit;
    private double amount;
    private String cardBrand;
    private String customerNumber;
    private String customerEmail;
    private String merchantNumber;
    private String merchantEmail;
    private String fraudOfficerNumber;
    private String fraudOfficerEmail;
    private String terminalId;
    private int fraudScore;
    public boolean isBadCard(String cardNumber) {
        System.out.println("Is Bad Card Test:"+cardNumber);
        return (cardNumber == null || !cardNumber.matches("\\d{16}"));
    }
}

