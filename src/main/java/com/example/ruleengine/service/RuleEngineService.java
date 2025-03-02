package com.example.ruleengine.service;

import com.example.ruleengine.model.Action;
import com.example.ruleengine.model.Purchase;
import com.example.ruleengine.model.Rule;
import com.example.ruleengine.util.CardChecker;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Service;

//import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Service
public class RuleEngineService {
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;
    private final ExpressionParser parser;
    private List<Rule> rules;

    public RuleEngineService(NotificationService notificationService) {
        this.notificationService = notificationService;
        this.objectMapper = new ObjectMapper();
        this.parser = new SpelExpressionParser();
    }

    @PostConstruct
    public void loadRules() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("rules.json");
            rules = objectMapper.readValue(inputStream, new TypeReference<List<Rule>>() {});
            System.out.println("✅ Rules loaded successfully from JSON!");
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to load rules.json", e);
        }
    }

    public void processPurchase(Purchase purchase) {
        for (Rule rule : rules) {
            System.out.println(rule.getCondition());
            Expression expression = parser.parseExpression(rule.getCondition());
            Boolean result = expression.getValue(purchase, Boolean.class);
            System.out.println(rule.getCondition()+"="+result);
            if (Boolean.TRUE.equals(result)) {
                for (Action action : rule.getActions()) {
                    notificationService.executeAction(action, purchase);
                }
            }
        }
    }
}

