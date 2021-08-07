package org.sydnik.createContract.data.contract;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SupplementaryAgreementBasicContract implements Serializable {
    private String dateCreateSupplementaryAgreementBasicContract;
    private int numberSupplementaryAgreementBasicContract;
    private int allSumInEURSupplementaryAgreement;
    private int allSumInBYNSupplementaryAgreement;
    private int prepaymentOr10PercentSumSupplementaryAgreement;
    private int payUpTo50PercentSumSupplementaryAgreement;
    private int payUpTo100PercentSumSupplementaryAgreement;

    public SupplementaryAgreementBasicContract(String dateCreateSupplementaryAgreementBasicContract,
                                               int numberSupplementaryAgreementBasicContract, int allSumInEURSupplementaryAgreement,
                                               int allSumInBYNSupplementaryAgreement, int prepaymentOr10PercentSumSupplementaryAgreement,
                                               int payUpTo50PercentSumSupplementaryAgreement, int payUpTo100PercentSumSupplementaryAgreement) {
        this.dateCreateSupplementaryAgreementBasicContract = dateCreateSupplementaryAgreementBasicContract;
        this.numberSupplementaryAgreementBasicContract = numberSupplementaryAgreementBasicContract;
        this.allSumInEURSupplementaryAgreement = allSumInEURSupplementaryAgreement;
        this.allSumInBYNSupplementaryAgreement = allSumInBYNSupplementaryAgreement;
        this.prepaymentOr10PercentSumSupplementaryAgreement = prepaymentOr10PercentSumSupplementaryAgreement;
        this.payUpTo50PercentSumSupplementaryAgreement = payUpTo50PercentSumSupplementaryAgreement;
        this.payUpTo100PercentSumSupplementaryAgreement = payUpTo100PercentSumSupplementaryAgreement;
    }

    public SupplementaryAgreementBasicContract() {
        this.dateCreateSupplementaryAgreementBasicContract = null;
        this.numberSupplementaryAgreementBasicContract = 0;
        this.allSumInEURSupplementaryAgreement = 0;
        this.allSumInBYNSupplementaryAgreement = 0;
        this.prepaymentOr10PercentSumSupplementaryAgreement = 0;
        this.payUpTo50PercentSumSupplementaryAgreement = 0;
        this.payUpTo100PercentSumSupplementaryAgreement = 0;
    }

    public String getDateCreateSupplementaryAgreementBasicContract() {
        if(dateCreateSupplementaryAgreementBasicContract==null||dateCreateSupplementaryAgreementBasicContract.equals("null")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(new Date());
        }
        return dateCreateSupplementaryAgreementBasicContract;
    }

    public int getNumberSupplementaryAgreementBasicContract() {
        return numberSupplementaryAgreementBasicContract;
    }

    public int getAllSumInEURSupplementaryAgreement() {
        return allSumInEURSupplementaryAgreement;
    }

    public int getAllSumInBYNSupplementaryAgreement() {
        return allSumInBYNSupplementaryAgreement;
    }

    public int getPrepaymentOr10PercentSumSupplementaryAgreement() {
        return prepaymentOr10PercentSumSupplementaryAgreement;
    }

    public int getPayUpTo50PercentSumSupplementaryAgreement() {
        return payUpTo50PercentSumSupplementaryAgreement;
    }

    public int getPayUpTo100PercentSumSupplementaryAgreement() {
        return payUpTo100PercentSumSupplementaryAgreement;
    }

    public StringBuilder dataForSave (){
        StringBuilder data = new StringBuilder();
        data.append("dateCreateSupplementaryAgreementBasicContract/=/").append(dateCreateSupplementaryAgreementBasicContract).append("\n");
        data.append("numberSupplementaryAgreementBasicContract/=/").append(numberSupplementaryAgreementBasicContract).append("\n");
        data.append("allSumInEURSupplementaryAgreement/=/").append(allSumInEURSupplementaryAgreement).append("\n");
        data.append("allSumInBYNSupplementaryAgreement/=/").append(allSumInBYNSupplementaryAgreement).append("\n");
        data.append("prepaymentOr10PercentSumSupplementaryAgreement/=/").append(prepaymentOr10PercentSumSupplementaryAgreement).append("\n");
        data.append("payUpTo50PercentSumSupplementaryAgreement/=/").append(payUpTo50PercentSumSupplementaryAgreement).append("\n");
        data.append("payUpTo100PercentSumSupplementaryAgreement/=/").append(payUpTo100PercentSumSupplementaryAgreement).append("\n");
        return data;
    }
    public static SupplementaryAgreementBasicContract load (Map<String,String> map) {
        try {
            return new SupplementaryAgreementBasicContract(
                    map.get("dateCreateSupplementaryAgreementBasicContract"), Integer.parseInt(map.get("numberSupplementaryAgreementBasicContract")),
                    Integer.parseInt(map.get("allSumInEURSupplementaryAgreement")), Integer.parseInt(map.get("allSumInBYNSupplementaryAgreement")),
                    Integer.parseInt(map.get("prepaymentOr10PercentSumSupplementaryAgreement")), Integer.parseInt(map.get("payUpTo50PercentSumSupplementaryAgreement")),
                    Integer.parseInt(map.get("payUpTo100PercentSumSupplementaryAgreement")));
        }catch (Exception e) {
            return new SupplementaryAgreementBasicContract();
        }
    }
}
