package com.example.ruleengine.service;

import com.example.ruleengine.entity.RuleEntity;
import com.example.ruleengine.entity.RuleHitEntity;
import com.example.ruleengine.entity.TrxEntity;
import com.example.ruleengine.model.Action;
import com.example.ruleengine.model.Purchase;
import com.example.ruleengine.model.Rule;
import com.example.ruleengine.repository.RuleHitRepository;
import com.example.ruleengine.repository.RuleRepository;
import com.example.ruleengine.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

//import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RuleEngineService {
    private static final Logger log = LoggerFactory.getLogger(RuleEngineService.class);
    private final ActionService actionService;
    //private  ObjectMapper objectMapper = new ObjectMapper();
    //private  ExpressionParser parser = new SpelExpressionParser();
    //private List<Rule> rules;

    private final RuleRepository ruleRepository;
    private final TransactionRepository trxRepository;
    private final RuleHitRepository ruleHitRepository;
    private final MethodService methodService;

    public void processPurchase(Purchase purchase) {
        //Save the transaction first
        TrxEntity trxEntity = TrxEntity.fromPurchase(purchase);
        trxRepository.save(trxEntity);
        //Load the rules and execute one by one
        ObjectMapper objectMapper = new ObjectMapper();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(purchase);
        context.setVariable("rules",methodService);
        List<RuleEntity> ruleList = ruleRepository.findByActiveTrue();
        try {
            for (RuleEntity rule : ruleList) {
                System.out.println(rule.getRuleJson());
                ObjectMapper mapper = new ObjectMapper();
                Rule spelRule = mapper.readValue(rule.getRuleJson(), Rule.class);
                log.info("Condition: " + spelRule.getCondition());
                for (Action action : spelRule.getActions()) {
                    log.info("Action Type: " + action.getType() + ", Recipient: " + action.getRecipient());
                }
                log.info("purchase.getTrxnType()="+purchase.getTrxnType()
                        +",rule.getTrxType()="+rule.getTrxType());
                if(rule.getTrxType().equals("BOTH") || rule.getTrxType().equals(purchase.getTrxnType())) {
                    Expression expression = parser.parseExpression(spelRule.getCondition());
                    //Boolean result = expression.getValue(purchase, Boolean.class);
                    Boolean result = expression.getValue(context, Boolean.class);
                    if (Boolean.TRUE.equals(result)) {
                        log.info("Rule:" + rule.getRuleTitle() + " hit");
                        RuleHitEntity ruleHitEntity = new RuleHitEntity();
                        ruleHitEntity.setTrxEntity(trxEntity);
                        ruleHitEntity.setRuleEntity(rule);
                        ruleHitRepository.save(ruleHitEntity);
                        for (Action action : spelRule.getActions()) {
                            log.info("Taking Action:" + action.getType());
                            actionService.executeAction(rule,trxEntity,action, purchase);
                        }
                    }
                } else {
                    log.info("Trx type not matched:purchase.getTrxnType()="+purchase.getTrxnType()
                            +", rule.getTrxType()="+rule.getTrxType());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error caught:" + ex.getMessage());
        }
    }

    public void updatePurchase(Purchase purchase) {
        List<TrxEntity> trxLst = trxRepository.findByUniqueId(purchase.getUniqueId());
        if (trxLst != null && trxLst.size() > 0) {
            TrxEntity trx = trxLst.get(0);
            trx.setRespCode(purchase.getRespCode());
            trxRepository.save(trx);
            log.info("Response code updated to:" + purchase.getRespCode() + " for the Transaction with Unique ID:" + purchase.getUniqueId() + "");
        } else {
            log.warn("No transaction found for the Unique ID:"+purchase.getUniqueId());
        }
    }

}

