package org.sydnik.createContract.data.contract;

import org.sydnik.createContract.data.AdditionalProduct;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class UpSaleContract implements Serializable {
    private String dateCreateUpSaleContract;
    private AdditionalProduct listAdditionalProducts[];
    private int allSumUpSaleInBYN;
    private int prepaymentUpSale;
    private int payUpTo100percentUpSale;

    public UpSaleContract(String dateCreateUpSaleContract, AdditionalProduct[] listAdditionalProducts,
                          int allSumUpSaleInBYN, int prepaymentUpSale, int payUpTo100percentUpSale) {
        this.dateCreateUpSaleContract = dateCreateUpSaleContract;
        this.listAdditionalProducts = listAdditionalProducts;
        this.allSumUpSaleInBYN = allSumUpSaleInBYN;
        this.prepaymentUpSale = prepaymentUpSale;
        this.payUpTo100percentUpSale = payUpTo100percentUpSale;
    }
    public UpSaleContract() {
        this.dateCreateUpSaleContract = null;
        this.listAdditionalProducts =null;
        this.allSumUpSaleInBYN = 0;
        this.prepaymentUpSale = 0;
        this.payUpTo100percentUpSale = 0;
    }
    public String getDateCreateUpSaleContract() {
        if(dateCreateUpSaleContract==null||dateCreateUpSaleContract.equals("null")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(new Date());
        }
        return dateCreateUpSaleContract;
    }

    public AdditionalProduct[] getListAdditionalProducts() {
        return listAdditionalProducts;
    }

    public int getAllSumUpSaleInBYN() {
        return allSumUpSaleInBYN;
    }

    public int getPrepaymentUpSale() {
        return prepaymentUpSale;
    }

    public int getPayUpTo100percentUpSale() {
        return payUpTo100percentUpSale;
    }
    public StringBuilder dataForSave (){
        StringBuilder data = new StringBuilder();
        data.append("dateCreateUpSaleContract/=/").append(dateCreateUpSaleContract).append("\n");
        data.append("allSumUpSaleInBYN/=/").append(allSumUpSaleInBYN).append("\n");
        data.append("prepaymentUpSale/=/").append(prepaymentUpSale).append("\n");
        data.append("payUpTo100percentUpSale/=/").append(payUpTo100percentUpSale).append("\n");
        try {
            for (int i = 0; i < listAdditionalProducts.length; i++) {
                data.append("listAdditionalProducts"+i+"/=/").append(listAdditionalProducts[i].dataForSave()).append("\n");
            }
        }catch (NullPointerException e){
            data.append("listAdditionalProducts0/=/").append("null").append("\n");
        }

        return data;
    }
    public static UpSaleContract load (Map<String,String> map){
        try {
            AdditionalProduct listAdditionalProducts[];
            if (map.get("listAdditionalProducts0").equals("null") || map.get("listAdditionalProducts0") == null) {
                return new UpSaleContract();
            } else {
                listAdditionalProducts = new AdditionalProduct[6];
                for (int i = 0; i < listAdditionalProducts.length; i++) {
                    listAdditionalProducts[i] = AdditionalProduct.Load(map.get("listAdditionalProducts" + i));
                }
                return new UpSaleContract(map.get("dateCreateUpSaleContract"), listAdditionalProducts,
                        Integer.parseInt(map.get("allSumUpSaleInBYN")), Integer.parseInt(map.get("prepaymentUpSale")),
                        Integer.parseInt(map.get("payUpTo100percentUpSale")));
            }
        }catch (Exception e){
            return new UpSaleContract();
        }
    }
}
