package com.example.ruleengine.controller;

import com.example.ruleengine.messaging.PurchaseProducer;
import com.example.ruleengine.model.Purchase;
import com.example.ruleengine.service.MethodService;
import com.example.ruleengine.service.RuleEngineService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PurchaseController {
    private final RuleEngineService ruleEngineService;

    private final MethodService methodService;

    private final PurchaseProducer purchaseProducer;

   /* public PurchaseController(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }*/

    @PostMapping("/purchase")
    public String processPurchaseSync(@RequestBody Purchase purchase) {
        System.out.println(purchase);
        ruleEngineService.processPurchase(purchase);
        return "Transaction processed.";
    }
    @PostMapping("/async_purchase")
    public ResponseEntity<String> processPurchaseAsync(@RequestBody Purchase purchase) {

        // Push to RabbitMQ
        purchaseProducer.publish(purchase);

        // Immediate response
        return ResponseEntity.ok("OK");
    }
    @PostMapping("/updatePurchase")
    public String processUpdatePurchase(@RequestBody Purchase purchase) {
        ruleEngineService.updatePurchase(purchase);
        return "Transaction Status Updated.";
    }
}

