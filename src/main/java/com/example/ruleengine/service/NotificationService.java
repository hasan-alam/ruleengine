package com.example.ruleengine.service;

import com.example.ruleengine.model.Action;
import com.example.ruleengine.model.Purchase;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void executeAction(Action action, Purchase purchase) {
        switch (action.getType()) {
            case "SMS":
                System.out.println("📩 SMS sent to " + action.getRecipient());
                break;
            case "EMAIL":
                System.out.println("📧 Email sent to " + action.getRecipient());
                break;
            case "BLOCK_CARD":
                System.out.println("🚫 Card blocked: " + purchase.getCardNumber());
                break;
            case "BLOCK_MERCHANT":
                System.out.println("🚫 Merchant blocked: " + purchase.getMerchantNumber());
                break;
            case "INCREASE_FRAUD_SCORE":
                System.out.println("🚫 Fraud score increased");
        }
    }
}

