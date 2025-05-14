package com.example.ruleengine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long trxId;
    private String cardNumber;
    private double cardLimit;
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
}
