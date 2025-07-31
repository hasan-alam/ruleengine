package com.example.ruleengine.model;

import com.example.ruleengine.service.MethodService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Data
public class Purchase {
    private double cardLimit;
    private String cardNumber;
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
    private String trxnType; //ACQ, ISS, BOTH
    private boolean onUsCard;
    private boolean onUsMerc;
    private String uniqueId;
    public boolean isBadCard(String cardNumber) {
        System.out.println("Is Bad Card Test:"+cardNumber);
        return (cardNumber == null || !cardNumber.matches("\\d{16}"));
    }
    public boolean isTrmAppTypeAmtExceeded(double trxAmt, double timeDuration) {
        System.out.println("appvType:"+appvType+" trxAmt:"+trxAmt+" timeDuration:"+timeDuration);
        return false;
    }

    public boolean isMerchantAppTypeAmtExceeded(double trxAmt, double timeDuration) {
        System.out.println("appvType:"+appvType+" trxAmt:"+trxAmt+" timeDuration:"+timeDuration);
        return false;
    }

    public boolean isCardAppTypeAmtExceeded(double trxAmt, double timeDuration) {
        System.out.println("appvType:"+appvType+" trxAmt:"+trxAmt+" timeDuration:"+timeDuration);
        return false;
    }

    public boolean isCardAppTypeAmtExceededForMcc(double trxAmt, double timeDuration) {
        System.out.println("appvType:"+appvType+" trxAmt:"+trxAmt+" timeDuration:"+timeDuration+" MCC:"+mcc);
        return false;
    }
    public boolean isTrmAppTypeCntExceeded(double trxAmt, double timeDuration) {
        System.out.println("appvType:"+appvType+" trxAmt:"+trxAmt+" timeDuration:"+timeDuration);
        return false;
    }

    public boolean isMerchantAppTypeCntExceeded(double trxAmt, double timeDuration) {
        System.out.println("appvType:"+appvType+" trxAmt:"+trxAmt+" timeDuration:"+timeDuration);
        return false;
    }

    public boolean isCardAppTypeCntExceeded(double trxAmt, double timeDuration) {
        System.out.println("appvType:"+appvType+" trxAmt:"+trxAmt+" timeDuration:"+timeDuration);
        return false;
    }

    public boolean isCardAppTypeCntExceededForMcc(double trxAmt, double timeDuration) {
        System.out.println("appvType:"+appvType+" trxAmt:"+trxAmt+" timeDuration:"+timeDuration+" MCC:"+mcc);
        return false;
    }

    /*public boolean cardRiskScoreGte(int score) {
        return methodService.isCardRiskScoreGte(score,cardNumber);
    }*/
}

