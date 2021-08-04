package org.sydnik.createContract.data.contract;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BasicContract implements Serializable {
    private String dateCreateContract;
    private String timeProduction;
    private int allSumInEUR;
    private int allSumInBYN;
    private int prepaymentOr10PercentSum;
    private int payUpTo50PercentSum;
    private int payUpTo100PercentSum;

    public BasicContract(String dateCreateContract, String timeProduction, int allSumInEUR,
                         int allSumInBYN, int prepaymentOr10PercentSum, int payUpTo50PercentSum, int payUpTo100PercentSum) {
        this.dateCreateContract = dateCreateContract;
        this.timeProduction = timeProduction;
        this.allSumInEUR = allSumInEUR;
        this.allSumInBYN = allSumInBYN;
        this.prepaymentOr10PercentSum = prepaymentOr10PercentSum;
        this.payUpTo50PercentSum = payUpTo50PercentSum;
        this.payUpTo100PercentSum = payUpTo100PercentSum;
    }

    public BasicContract() {
        this.dateCreateContract = null;
        this.timeProduction = null;
        this.allSumInEUR = 0;
        this.allSumInBYN = 0;
        this.prepaymentOr10PercentSum = 0;
        this.payUpTo50PercentSum = 0;
        this.payUpTo100PercentSum = 0;
    }


    public String getDateCreateContract() {
        if(dateCreateContract==null||dateCreateContract.equals("null")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(new Date());
        }
        return dateCreateContract;
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

    public int getPrepaymentOr10PercentSum() {
        return prepaymentOr10PercentSum;
    }

    public int getPayUpTo50PercentSum() {
        return payUpTo50PercentSum;
    }

    public int getPayUpTo100PercentSum() {
        return payUpTo100PercentSum;
    }

    public StringBuilder dataForSave (){
        StringBuilder data = new StringBuilder();
        data.append("dateCreateContract/=/").append(dateCreateContract).append("\n");
        data.append("timeProduction/=/").append(timeProduction).append("\n");
        data.append("allSumInEUR/=/").append(allSumInEUR).append("\n");
        data.append("allSumInBYN/=/").append(allSumInBYN).append("\n");
        data.append("prepaymentOr10PercentSum/=/").append(prepaymentOr10PercentSum).append("\n");
        data.append("payUpTo50PercentSum/=/").append(payUpTo50PercentSum).append("\n");
        data.append("payUpTo100PercentSum/=/").append(payUpTo100PercentSum).append("\n");
        return data;
    }
    public static BasicContract load (Map<String,String> map){
        return new BasicContract(map.get("dateCreateContract"),map.get("timeProduction"),
                Integer.parseInt(map.get("allSumInEUR")), Integer.parseInt(map.get("allSumInBYN")),
                Integer.parseInt(map.get("prepaymentOr10PercentSum")),Integer.parseInt(map.get("payUpTo50PercentSum")),
                Integer.parseInt(map.get("payUpTo100PercentSum")));


    }
}
