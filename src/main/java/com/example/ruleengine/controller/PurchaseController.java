package com.example.ruleengine.controller;

import com.example.ruleengine.model.Purchase;
import com.example.ruleengine.service.RuleEngineService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    private final RuleEngineService ruleEngineService;

    public PurchaseController(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }

    @PostMapping("/purchase")
    public String processPurchase(@RequestBody Purchase purchase) {
        System.out.println(purchase);
        ruleEngineService.processPurchase(purchase);
        return "Transaction processed.";
    }
}

