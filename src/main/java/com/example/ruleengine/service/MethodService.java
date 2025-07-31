package com.example.ruleengine.service;

import com.example.ruleengine.entity.CardScore;
import com.example.ruleengine.entity.MerchantScore;
import com.example.ruleengine.model.Purchase;
import com.example.ruleengine.repository.CardScoreRepository;
import com.example.ruleengine.repository.MerchantScoreRepository;
import com.example.ruleengine.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class MethodService {
    private static final Logger log = LoggerFactory.getLogger(MethodService.class);
    private final CardScoreRepository cardScoreRepository;

    private final MerchantScoreRepository merchantScoreRepository;

    private final TransactionRepository transactionRepository;

    public boolean isCardRiskScoreGte(Purchase purchase, int score) {
        log.info("Evaluating isCardRiskScoreGte for score>=" + score + " for card:" + purchase.getCardNumber());
        Optional<CardScore> cardScore = cardScoreRepository.findById(purchase.getCardNumber());
        if (cardScore.isPresent()) {
            log.info("Score found for card:" + purchase.getCardNumber());
            return cardScore.get().getScore() >= score;
        }
        return false;
    }

    public boolean isMerchantRiskScoreGte(Purchase purchase, int score) {
        log.info("Evaluating isMerchantRiskScoreGte for score>=" + score + " for merchant:" + purchase.getMercID());
        Optional<MerchantScore> merchantScore = merchantScoreRepository.findById(purchase.getMercID());
        if (merchantScore.isPresent()) {
            log.info("Score found for merchant:" + purchase.getMercID());
            return merchantScore.get().getScore() >= score;
        }
        return false;
    }

    public boolean isBinAttackFailCntinMinutes(Purchase purchase, int failCnt, int minutes) {

        String currentTimeHHMMSS = purchase.getTxntimeHHMMSS();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

// parse current time
        LocalTime currentTime = LocalTime.parse(currentTimeHHMMSS, timeFormatter);

// minus 10 minutes
        LocalTime startTime = currentTime.minusMinutes(10);

// back to HHMMSS string
        String startTimeHHMMSS = startTime.format(timeFormatter);
        long failedCount = transactionRepository.countFailedTxnsForBin(purchase.getCardNumber().substring(0, 6), purchase.getTxndateYYYYMMDD(), startTimeHHMMSS, currentTimeHHMMSS);
        log.info("Failed transaction for BIN:" + purchase.getCardNumber().substring(0, 6) + " in last " + minutes + " is " + failedCount);
        if (failedCount >= failCnt) {
            log.info("Potential BIN attack identified");
            return true;
        }
        return false;
    }
}
