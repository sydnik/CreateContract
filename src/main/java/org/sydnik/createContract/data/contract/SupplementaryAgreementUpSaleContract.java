package org.sydnik.createContract.data.contract;

import org.sydnik.createContract.data.AdditionalProduct;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SupplementaryAgreementUpSaleContract implements Serializable {
    private String dateCreateSupplementaryAgreementUpSaleContract;
    private int numberSupplementaryAgreementUpSale;
    private AdditionalProduct listAdditionalProductsSupplementaryAgreementUpSale[];
    private int allSumUpSaleInBYNSupplementaryAgreement;
    private int prepaymentUpSaleSupplementaryAgreement;
    private int payUpTo100percentUpSaleSupplementaryAgreement;

    public SupplementaryAgreementUpSaleContract(String dateCreateSupplementaryAgreementUpSaleContract,
                                                int numberSupplementaryAgreementUpSale, AdditionalProduct[] listAdditionalProductsSupplementaryAgreementUpSale,
                                                int allSumUpSaleInBYNSupplementaryAgreement, int prepaymentUpSaleSupplementaryAgreement,
                                                int payUpTo100percentUpSaleSupplementaryAgreement) {
        this.dateCreateSupplementaryAgreementUpSaleContract = dateCreateSupplementaryAgreementUpSaleContract;
        this.numberSupplementaryAgreementUpSale = numberSupplementaryAgreementUpSale;
        this.listAdditionalProductsSupplementaryAgreementUpSale = listAdditionalProductsSupplementaryAgreementUpSale;
        this.allSumUpSaleInBYNSupplementaryAgreement = allSumUpSaleInBYNSupplementaryAgreement;
        this.prepaymentUpSaleSupplementaryAgreement = prepaymentUpSaleSupplementaryAgreement;
        this.payUpTo100percentUpSaleSupplementaryAgreement = payUpTo100percentUpSaleSupplementaryAgreement;
    }

    public SupplementaryAgreementUpSaleContract() {
        this.dateCreateSupplementaryAgreementUpSaleContract = null;
        this.numberSupplementaryAgreementUpSale = 0;
        this.listAdditionalProductsSupplementaryAgreementUpSale = null;
        this.allSumUpSaleInBYNSupplementaryAgreement = 0;
        this.prepaymentUpSaleSupplementaryAgreement = 0;
        this.payUpTo100percentUpSaleSupplementaryAgreement = 0;
    }

    public String getDateCreateSupplementaryAgreementUpSaleContract() {
        if(dateCreateSupplementaryAgreementUpSaleContract==null||dateCreateSupplementaryAgreementUpSaleContract.equals("null")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(new Date());
        }
        return dateCreateSupplementaryAgreementUpSaleContract;
    }

    public int getNumberSupplementaryAgreementUpSale() {
        return numberSupplementaryAgreementUpSale;
    }

    public AdditionalProduct[] getListAdditionalProductsSupplementaryAgreementUpSale() {
        return listAdditionalProductsSupplementaryAgreementUpSale;
    }

    public int getAllSumUpSaleInBYNSupplementaryAgreement() {
        return allSumUpSaleInBYNSupplementaryAgreement;
    }

    public int getPrepaymentUpSaleSupplementaryAgreement() {
        return prepaymentUpSaleSupplementaryAgreement;
    }

    public int getPayUpTo100percentUpSaleSupplementaryAgreement() {
        return payUpTo100percentUpSaleSupplementaryAgreement;
    }
    public StringBuilder dataForSave (){
        StringBuilder data = new StringBuilder();
        data.append("dateCreateSupplementaryAgreementUpSaleContract/=/").append(dateCreateSupplementaryAgreementUpSaleContract).append("\n");
        data.append("numberSupplementaryAgreementUpSale/=/").append(numberSupplementaryAgreementUpSale).append("\n");
        data.append("allSumUpSaleInBYNSupplementaryAgreement/=/").append(allSumUpSaleInBYNSupplementaryAgreement).append("\n");
        data.append("prepaymentUpSaleSupplementaryAgreement/=/").append(prepaymentUpSaleSupplementaryAgreement).append("\n");
        data.append("payUpTo100percentUpSaleSupplementaryAgreement/=/").append(payUpTo100percentUpSaleSupplementaryAgreement).append("\n");
        try {
              for (int i = 0; i < listAdditionalProductsSupplementaryAgreementUpSale.length; i++) {
              data.append("listAdditionalProductsSupplementaryAgreementUpSale"+i+"/=/").append(listAdditionalProductsSupplementaryAgreementUpSale[i].dataForSave()).append("\n");
            }
        }
        catch (NullPointerException e){
        data.append("listAdditionalProductsSupplementaryAgreementUpSale0/=/").append("null").append("\n");
        }
        return data;
    }
    public static SupplementaryAgreementUpSaleContract load (Map<String,String> map){
        try {
            AdditionalProduct listAdditionalProductsSupplementaryAgreementUpSale[];
            if (map.get("listAdditionalProductsSupplementaryAgreementUpSale0").equals("null")) {
                return new SupplementaryAgreementUpSaleContract();
            } else {
                listAdditionalProductsSupplementaryAgreementUpSale = new AdditionalProduct[6];
                for (int i = 0; i < listAdditionalProductsSupplementaryAgreementUpSale.length; i++) {
                    listAdditionalProductsSupplementaryAgreementUpSale[i] = AdditionalProduct.Load(map.get("listAdditionalProductsSupplementaryAgreementUpSale" + i));
                }
                return new SupplementaryAgreementUpSaleContract(
                        map.get("dateCreateSupplementaryAgreementUpSaleContract"), Integer.parseInt(map.get("numberSupplementaryAgreementUpSale")),
                        listAdditionalProductsSupplementaryAgreementUpSale, Integer.parseInt(map.get("allSumUpSaleInBYNSupplementaryAgreement")),
                        Integer.parseInt(map.get("prepaymentUpSaleSupplementaryAgreement")), Integer.parseInt(map.get("payUpTo100percentUpSaleSupplementaryAgreement")));
            }
        } catch (Exception e) {
            return new SupplementaryAgreementUpSaleContract();
        }
    }
}
