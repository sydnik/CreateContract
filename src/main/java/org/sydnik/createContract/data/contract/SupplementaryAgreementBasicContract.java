package org.sydnik.createContract.data.contract;

import java.util.Map;

public class SupplementaryAgreementBasicContract extends BasicContract {
    private int number;

    public SupplementaryAgreementBasicContract(String dateCreate, String timeProduction, int allSumInEUR, int allSumInBYN, int prepayment, int payUpTo50Percent, int payUpTo100Percent, int number) {
        super(dateCreate, timeProduction, allSumInEUR, allSumInBYN, prepayment, payUpTo50Percent, payUpTo100Percent);
        this.number = number;
    }
    public SupplementaryAgreementBasicContract() {
        super();
        this.number = 0;
    }
    public int getNumber() {
        if(number ==0){
            return 1;
        }
        return number;
    }
    public StringBuilder getDataForSave(){
        StringBuilder data = new StringBuilder();
        data.append("dateCreateSupplementaryAgreementBasicContract/=/").append(dateCreate).append("\n");
        data.append("numberSupplementaryAgreementBasicContract/=/").append(number).append("\n");
        data.append("allSumInEURSupplementaryAgreement/=/").append(allSumInEUR).append("\n");
        data.append("allSumInBYNSupplementaryAgreement/=/").append(allSumInBYN).append("\n");
        data.append("prepaymentOr10PercentSumSupplementaryAgreement/=/").append(prepayment).append("\n");
        data.append("payUpTo50PercentSumSupplementaryAgreement/=/").append(payUpTo50Percent).append("\n");
        data.append("payUpTo100PercentSumSupplementaryAgreement/=/").append(payUpTo100Percent).append("\n");
        return data;
    }
    public static SupplementaryAgreementBasicContract load (Map<String,String> map) {
        try {
            return new SupplementaryAgreementBasicContract(
                    map.get("dateCreateSupplementaryAgreementBasicContract"),TIME_PRODUCT[0],
                    Integer.parseInt(map.get("allSumInEURSupplementaryAgreement")), Integer.parseInt(map.get("allSumInBYNSupplementaryAgreement")),
                    Integer.parseInt(map.get("prepaymentOr10PercentSumSupplementaryAgreement")), Integer.parseInt(map.get("payUpTo50PercentSumSupplementaryAgreement")),
                    Integer.parseInt(map.get("payUpTo100PercentSumSupplementaryAgreement")),Integer.parseInt(map.get("numberSupplementaryAgreementBasicContract")));
        }catch (Exception e) {
            return new SupplementaryAgreementBasicContract();
        }
    }

}
