package com.example.ruleengine.service;

import com.example.ruleengine.entity.*;
import com.example.ruleengine.model.Action;
import com.example.ruleengine.model.Purchase;
import com.example.ruleengine.repository.CardScoreRepository;
import com.example.ruleengine.repository.MerchantScoreRepository;
import com.example.ruleengine.repository.RuleActionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActionService {
    private static final Logger log = LoggerFactory.getLogger(ActionService.class);
    private final MerchantScoreRepository merchantScoreRepository;
    private final CardScoreRepository cardScoreRepository;

    private final RuleActionRepository ruleActionRepository;
    public void executeAction(RuleEntity rule,TrxEntity trxEntity,Action action, Purchase purchase) {
        switch (action.getType()) {
            case "SMS":
                System.out.println("📩 SMS sent to " + action.getRecipient());
                break;
            case "EMAIL":
                System.out.println("📧 Email sent to " + action.getRecipient());
                break;
            case "BLOCK_CARD":
                blockCard(purchase,action,rule,trxEntity);
                break;
            case "BLOCK_MERCHANT":
                blockMerchant(purchase,action,rule,trxEntity);
                break;
            case "BLOCK_TERMINAL":
                blockTerminal(purchase,action,rule,trxEntity);
                break;
            case "BLOCK_TRX":
                blockTransaction(purchase,action,rule,trxEntity);
                break;
            case "INCREASE_FRAUD_SCORE":
                increaseRiskScore(purchase,action,rule,trxEntity);
        }
    }

    private void sendNotification(Purchase purchase, Action action, RuleEntity rule, TrxEntity trxEntity) {
        log.info("Sending notification using "+action.getType()+" to "+ action.getRecipient());
        saveAction(purchase,action,rule,trxEntity);
    }
    private void blockCard(Purchase purchase, Action action, RuleEntity rule, TrxEntity trxEntity) {
        log.info("Please Block the Card");
        saveAction(purchase,action,rule,trxEntity);
    }

    private void blockMerchant(Purchase purchase, Action action, RuleEntity rule, TrxEntity trxEntity) {
        log.info("Please Block the Merchant");
        saveAction(purchase,action,rule,trxEntity);
    }

    private void blockTerminal(Purchase purchase, Action action, RuleEntity rule, TrxEntity trxEntity) {
        log.info("Please Block the Terminal");
        saveAction(purchase,action,rule,trxEntity);
    }

    private void blockTransaction(Purchase purchase, Action action, RuleEntity rule, TrxEntity trxEntity) {
        log.info("Please Block the Transaction");
        saveAction(purchase,action,rule,trxEntity);
    }
    private void increaseRiskScore(Purchase purchase, Action action, RuleEntity rule, TrxEntity trxEntity) {
        if(purchase.isOnUsCard()) {
            CardScore cardScore = cardScoreRepository.findById(purchase.getCardNumber()).orElse(new CardScore());
            cardScore.setCardNumber(purchase.getCardNumber());
            cardScore.setScore(cardScore.getScore()+action.getScore());
            cardScoreRepository.save(cardScore);
            log.info("Score:"+action.getScore()+" increased for card");
        }
        if(purchase.isOnUsMerc()) {
            MerchantScore merchantScore = merchantScoreRepository.findById(purchase.getCardNumber()).orElse(new MerchantScore());
            merchantScore.setMerchantId(purchase.getCardNumber());
            merchantScore.setScore(merchantScore.getScore()+action.getScore());
            merchantScoreRepository.save(merchantScore);
            log.info("Score:"+action.getScore()+" increased for merchant");
        }
        saveAction(purchase,action,rule,trxEntity);
    }
    private void saveAction(Purchase purchase, Action action, RuleEntity rule, TrxEntity trxEntity) {
        RuleActionEntity ruleAction = new RuleActionEntity();
        ruleAction.setAction(action.getType());
        ruleAction.setRuleEntity(rule);
        ruleAction.setTrxEntity(trxEntity);
        ruleActionRepository.save(ruleAction);
    }
}

