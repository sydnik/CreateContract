package org.sydnik.createContract.data.contract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class InvoiceDocument {
    private int priceBYNInvoiceDocument;
    private int priceInEURInvoiceDocument;
    private double vat20InvoiceDocument;
    private String createDateInvoiceDocument;
    private String whichBank;

    public InvoiceDocument(int priceBYNInvoiceDocument,int priceInEURInvoiceDocument, double vat20InvoiceDocument, String createDateInvoiceDocument, String whichBank) {
        this.priceBYNInvoiceDocument = priceBYNInvoiceDocument;
        this.priceInEURInvoiceDocument = priceInEURInvoiceDocument;
        this.vat20InvoiceDocument = vat20InvoiceDocument;
        this.createDateInvoiceDocument = createDateInvoiceDocument;
        this.whichBank = whichBank;
    }
    public InvoiceDocument() {
        this.priceBYNInvoiceDocument = 0;
        this.priceInEURInvoiceDocument = 0;
        this.vat20InvoiceDocument = 0;
        this.createDateInvoiceDocument = null;
        this.whichBank = "";
    }

    public int getPriceBYNInvoiceDocument() {
        return priceBYNInvoiceDocument;
    }

    public double getVat20InvoiceDocument() {
        return vat20InvoiceDocument;
    }

    public String getCreateDateInvoiceDocument() {
        if(createDateInvoiceDocument==null||createDateInvoiceDocument.equals("null")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(new Date());
        }
        return createDateInvoiceDocument;
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

    public int getPriceInEURInvoiceDocument() {
        return priceInEURInvoiceDocument;
    }

    public StringBuilder dataForSave (){
        StringBuilder data = new StringBuilder();
        data.append("priceBYNInvoiceDocument/=/").append(priceBYNInvoiceDocument).append("\n");
        data.append("priceInEURInvoiceDocument/=/").append(priceInEURInvoiceDocument).append("\n");
        data.append("vat20InvoiceDocument/=/").append(vat20InvoiceDocument).append("\n");
        data.append("createDateInvoiceDocument/=/").append(createDateInvoiceDocument).append("\n");
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
