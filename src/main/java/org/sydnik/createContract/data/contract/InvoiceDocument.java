package org.sydnik.createContract.data.contract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class InvoiceDocument {
    private int priceBYN;
    private int priceInEUR;
    private double vat20;
    private String createDate;
    private String whichBank;

    public InvoiceDocument(int priceBYN, int priceInEUR, double vat20, String createDate, String whichBank) {
        this.priceBYN = priceBYN;
        this.priceInEUR = priceInEUR;
        this.vat20 = vat20;
        this.createDate = createDate;
        this.whichBank = whichBank;
    }
    public InvoiceDocument() {
        this.priceBYN = 0;
        this.priceInEUR = 0;
        this.vat20 = 0;
        this.createDate = null;
        this.whichBank = "";
    }

    public int getPriceBYN() {
        return priceBYN;
    }
    public double getVat20() {
        return vat20;
    }
    public String getCreateDate() {
        if(createDate ==null|| createDate.equals("null")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(new Date());
        }
        return createDate;
    }
    public String getWhichBank() {
        return whichBank;
    }
    public String getLineForBank(){
        if (whichBank.equals("Беларусбанк")){
            return "Продажа товара осуществляется с привлечением кредита ОАО \"АСБ Беларусбанк\" \"Партнёр Оптимальный\"";
        }
        return " ";
    }
    public int getPriceInEUR() {
        return priceInEUR;
    }

    public StringBuilder getDataForSave(){
        StringBuilder data = new StringBuilder();
        data.append("priceBYNInvoiceDocument/=/").append(priceBYN).append("\n");
        data.append("priceInEURInvoiceDocument/=/").append(priceInEUR).append("\n");
        data.append("vat20InvoiceDocument/=/").append(vat20).append("\n");
        data.append("createDateInvoiceDocument/=/").append(createDate).append("\n");
        data.append("whichBank/=/").append(whichBank).append("\n");
        return data;
    }

    public static InvoiceDocument load (Map<String,String> map){
        try {
            return new InvoiceDocument(Integer.parseInt(map.get("priceBYNInvoiceDocument")),
                    Integer.parseInt(map.get("priceInEURInvoiceDocument")),
                    Double.parseDouble(map.get("vat20InvoiceDocument")),
                    map.get("createDateInvoiceDocument"),
                    map.get("whichBank"));
        }catch (Exception e){
            return new InvoiceDocument();
        }

    }

}
