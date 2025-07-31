package com.example.ruleengine.entity;

import com.example.ruleengine.model.Purchase;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "trxdata")
@Data
public class TrxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long trxId;
    @OneToMany(mappedBy = "trxEntity")
    private List<RuleHitEntity> ruleHitEntityList;
    private String cardNumber;
    private double cardLimit;
    private double merchDailyLimit;
    private double trmDailyLimit;
    private double txnAmount;
    private String cardBrand;
    private String customerNumber;
    private String customerEmail;
    private String merchantNumber;
    private String merchantEmail;
    private String fraudOfficerNumber;
    private String fraudOfficerEmail;
    private String terminalId;
    private String acquirerID;
    private String appvType;
    private String mcc;
    private double billAmount;
    private String custClass;
    private String mercID;
    private String mercIndustryCode;
    private String mercName;
    private String monitorCode;
    private String posEntryMode;
    private String currCode;
    private String txndateYYYYMMDD;
    private String cntryCode;
    private String respCode;
    private String txnSource;
    private String txntimeHHMMSS;
    private String trxnType;
    private boolean onUsCard;
    private boolean onUsMerc;
    @Column(unique = true, nullable = false)
    private String uniqueId;

    public static TrxEntity fromPurchase(Purchase purchase) {
        TrxEntity entity = new TrxEntity();
        entity.setCardNumber(purchase.getCardNumber());
        entity.setCardLimit(purchase.getCardLimit());
        entity.setMerchDailyLimit(purchase.getMerchDailyLimit());
        entity.setTrmDailyLimit(purchase.getTrmDailyLimit());
        entity.setTxnAmount(purchase.getTxnAmount());
        entity.setCardBrand(purchase.getCardBrand());
        entity.setCustomerNumber(purchase.getCustomerNumber());
        entity.setCustomerEmail(purchase.getCustomerEmail());
        entity.setMerchantNumber(purchase.getMerchantNumber());
        entity.setMerchantEmail(purchase.getMerchantEmail());
        entity.setFraudOfficerNumber(purchase.getFraudOfficerNumber());
        entity.setFraudOfficerEmail(purchase.getFraudOfficerEmail());
        entity.setTerminalId(purchase.getTerminalId());
        entity.setAcquirerID(purchase.getAcquirerID());
        entity.setAppvType(purchase.getAppvType());
        entity.setMcc(purchase.getMcc());
        entity.setBillAmount(purchase.getBillAmount());
        entity.setCustClass(purchase.getCustClass());
        entity.setMercID(purchase.getMercID());
        entity.setMercIndustryCode(purchase.getMercIndustryCode());
        entity.setMercName(purchase.getMercName());
        entity.setMonitorCode(purchase.getMonitorCode());
        entity.setPosEntryMode(purchase.getPosEntryMode());
        entity.setCurrCode(purchase.getCurrCode());
        entity.setTxndateYYYYMMDD(purchase.getTxndateYYYYMMDD());
        entity.setCntryCode(purchase.getCntryCode());
        entity.setRespCode(purchase.getRespCode());
        entity.setTxnSource(purchase.getTxnSource());
        entity.setTxntimeHHMMSS(purchase.getTxntimeHHMMSS());
        entity.setTrxnType(purchase.getTrxnType());
        entity.setOnUsCard(purchase.isOnUsCard());
        entity.setOnUsMerc(purchase.isOnUsMerc());
        entity.setUniqueId(purchase.getUniqueId());
        return entity;
    }
}
