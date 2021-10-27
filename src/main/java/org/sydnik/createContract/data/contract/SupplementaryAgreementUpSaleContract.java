package org.sydnik.createContract.data.contract;

import org.sydnik.createContract.data.AdditionalProduct;
import org.sydnik.createContract.view.document.ViewSupplementaryAgreementUpSale;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SupplementaryAgreementUpSaleContract extends UpSaleContract implements Serializable {
    private int number;

    public SupplementaryAgreementUpSaleContract() {
        super();
        this.number = 0;
    }

    public SupplementaryAgreementUpSaleContract(String dateCreate, AdditionalProduct[] listAdditionalProducts, int allSumBYN, int prepayment, int payUpTo100percent, int number) {
        super(dateCreate, listAdditionalProducts, allSumBYN, prepayment, payUpTo100percent);
        this.number = number;
    }

    public int getNumber() {
        if(number ==0){
            return 1;
        }
        return number;
    }
    public StringBuilder getDataForSave(){
        StringBuilder data = new StringBuilder();
        data.append("dateCreateSupplementaryAgreementUpSaleContract/=/").append(dateCreate).append("\n");
        data.append("numberSupplementaryAgreementUpSale/=/").append(number).append("\n");
        data.append("allSumUpSaleInBYNSupplementaryAgreement/=/").append(allSumBYN).append("\n");
        data.append("prepaymentUpSaleSupplementaryAgreement/=/").append(prepayment).append("\n");
        data.append("payUpTo100percentUpSaleSupplementaryAgreement/=/").append(payUpTo100percent).append("\n");
        try {
              for (int i = 0; i < listAdditionalProducts.length; i++) {
              data.append("listAdditionalProductsSupplementaryAgreementUpSale"+i+"/=/").append(listAdditionalProducts[i].getDataForSave()).append("\n");
            }
        }
        catch (NullPointerException e){
        data.append("listAdditionalProductsSupplementaryAgreementUpSale0/=/").append("null").append("\n");
        }
        return data;
    }

    public static SupplementaryAgreementUpSaleContract load (Map<String,String> map){
        try {
            AdditionalProduct listAdditionalProducts[];
            if (map.get("listAdditionalProductsSupplementaryAgreementUpSale0").equals("null")) {
                return new SupplementaryAgreementUpSaleContract();
            } else {
                listAdditionalProducts = new AdditionalProduct[ViewSupplementaryAgreementUpSale.ROW_ADDITIONAL_PRODUCT];
                for (int i = 0; i < listAdditionalProducts.length; i++) {
                    listAdditionalProducts[i] = AdditionalProduct.Load(map.get("listAdditionalProductsSupplementaryAgreementUpSale" + i));
                }
                return new SupplementaryAgreementUpSaleContract(map.get("dateCreateSupplementaryAgreementUpSaleContract"),
                        listAdditionalProducts,
                        Integer.parseInt(map.get("allSumUpSaleInBYNSupplementaryAgreement")),
                        Integer.parseInt(map.get("prepaymentUpSaleSupplementaryAgreement")),
                        Integer.parseInt(map.get("payUpTo100percentUpSaleSupplementaryAgreement")),
                        Integer.parseInt(map.get("numberSupplementaryAgreementUpSale")));
            }
        } catch (Exception e) {
            return new SupplementaryAgreementUpSaleContract();
        }
    }
}


