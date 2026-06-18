package com.example.ruleengine.messaging;

import com.example.ruleengine.model.Purchase;
import com.example.ruleengine.service.RuleEngineService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class PurchaseConsumer {

    private final RuleEngineService ruleEngineService;

    @Bean
    public Consumer<Purchase> purchaseIn() {
        return purchase -> {
            System.out.println("Received from RabbitMQ: " + purchase);
            ruleEngineService.processPurchase(purchase);
        };
    }
}
