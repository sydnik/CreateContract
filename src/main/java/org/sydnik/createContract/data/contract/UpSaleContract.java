package org.sydnik.createContract.data.contract;

import org.sydnik.createContract.data.AdditionalProduct;
import org.sydnik.createContract.view.document.ViewUpSaleContract;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class UpSaleContract implements Serializable {
    protected String dateCreate;
    protected AdditionalProduct listAdditionalProducts[];
    protected int allSumBYN;
    protected int prepayment;
    protected int payUpTo100percent;

    public UpSaleContract(String dateCreate, AdditionalProduct[] listAdditionalProducts,
                          int allSumBYN, int prepayment, int payUpTo100percent) {
        this.dateCreate = dateCreate;
        this.listAdditionalProducts = listAdditionalProducts;
        this.allSumBYN = allSumBYN;
        this.prepayment = prepayment;
        this.payUpTo100percent = payUpTo100percent;
    }
    public UpSaleContract() {
        this.dateCreate = null;
        this.listAdditionalProducts =null;
        this.allSumBYN = 0;
        this.prepayment = 0;
        this.payUpTo100percent = 0;
    }


    public String getDateCreate() {
        if(dateCreate ==null|| dateCreate.equals("null")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(new Date());
        }
        return dateCreate;
    }
    public AdditionalProduct[] getListAdditionalProducts() {
        return listAdditionalProducts;
    }
    public int getAllSumBYN() {
        return allSumBYN;
    }
    public int getPrepayment() {
        return prepayment;
    }
    public int getPayUpTo100percent() {
        return payUpTo100percent;
    }

    public StringBuilder getDataForSave(){
        StringBuilder data = new StringBuilder();
        data.append("dateCreateUpSaleContract/=/").append(dateCreate).append("\n");
        data.append("allSumUpSaleInBYN/=/").append(allSumBYN).append("\n");
        data.append("prepaymentUpSale/=/").append(prepayment).append("\n");
        data.append("payUpTo100percentUpSale/=/").append(payUpTo100percent).append("\n");
        try {
            for (int i = 0; i < listAdditionalProducts.length; i++) {
                data.append("listAdditionalProducts"+i+"/=/").append(listAdditionalProducts[i].getDataForSave()).append("\n");
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
            }
            else {
                listAdditionalProducts = new AdditionalProduct[ViewUpSaleContract.ROW_ADDITIONAL_PRODUCT];
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
