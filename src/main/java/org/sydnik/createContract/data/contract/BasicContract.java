package org.sydnik.createContract.data.contract;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BasicContract implements Serializable {
    public final static String[] TIME_PRODUCT = {"14 до 25","21 до 29"};

    protected String dateCreate;
    protected String timeProduction;
    protected int allSumInEUR;
    protected int allSumInBYN;
    protected int prepayment;
    protected int payUpTo50Percent;
    protected int payUpTo100Percent;

    public BasicContract(String dateCreate, String timeProduction, int allSumInEUR,
                         int allSumInBYN, int prepayment, int payUpTo50Percent, int payUpTo100Percent) {
        this.dateCreate = dateCreate;
        this.timeProduction = timeProduction;
        this.allSumInEUR = allSumInEUR;
        this.allSumInBYN = allSumInBYN;
        this.prepayment = prepayment;
        this.payUpTo50Percent = payUpTo50Percent;
        this.payUpTo100Percent = payUpTo100Percent;
    }

    public BasicContract() {
        this.dateCreate = null;
        this.timeProduction = null;
        this.allSumInEUR = 0;
        this.allSumInBYN = 0;
        this.prepayment = 0;
        this.payUpTo50Percent = 0;
        this.payUpTo100Percent = 0;
    }


    public String getDateCreate() {
        if(dateCreate ==null|| dateCreate.equals("null")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(new Date());
        }
        return dateCreate;
    }
    public String getTimeProduction() {
        return timeProduction;
    }
    public int getAllSumInEUR() {
        return allSumInEUR;
    }
    public int getAllSumInBYN() {
        return allSumInBYN;
    }
    public int getPrepayment() {
        return prepayment;
    }
    public int getPayUpTo50Percent() {
        return payUpTo50Percent;
    }
    public int getPayUpTo100Percent() {
        return payUpTo100Percent;
    }

    public StringBuilder getDataForSave(){
        StringBuilder data = new StringBuilder();
        data.append("dateCreateContract/=/").append(dateCreate).append("\n");
        data.append("timeProduction/=/").append(timeProduction).append("\n");
        data.append("allSumInEUR/=/").append(allSumInEUR).append("\n");
        data.append("allSumInBYN/=/").append(allSumInBYN).append("\n");
        data.append("prepaymentOr10PercentSum/=/").append(prepayment).append("\n");
        data.append("payUpTo50PercentSum/=/").append(payUpTo50Percent).append("\n");
        data.append("payUpTo100PercentSum/=/").append(payUpTo100Percent).append("\n");
        return data;
    }
    public static BasicContract load (Map<String,String> map){
        try {
            return new BasicContract(map.get("dateCreateContract"),map.get("timeProduction"),
                    Integer.parseInt(map.get("allSumInEUR")), Integer.parseInt(map.get("allSumInBYN")),
                    Integer.parseInt(map.get("prepaymentOr10PercentSum")),Integer.parseInt(map.get("payUpTo50PercentSum")),
                    Integer.parseInt(map.get("payUpTo100PercentSum")));
        }catch (Exception e){
            return new BasicContract();
        }

    }
}
