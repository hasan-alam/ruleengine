package com.example.ruleengine.util;

public class CardChecker {
    public boolean isBadCard(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }
}
