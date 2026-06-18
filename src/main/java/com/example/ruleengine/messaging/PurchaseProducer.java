package com.example.ruleengine.messaging;

import com.example.ruleengine.model.Purchase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

@Configuration
public class PurchaseProducer {

    private Purchase purchase;

    public void publish(Purchase purchase) {
        this.purchase = purchase;
    }

    @Bean
    public Supplier<Message<Purchase>> purchaseOut() {
        return () -> {
            if (purchase == null) {
                return null;
            }
            Message<Purchase> message = MessageBuilder.withPayload(purchase).build();
            purchase = null; // reset after send
            return message;
        };
    }
}
