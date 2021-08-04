package org.sydnik.createContract.data;

import java.io.Serializable;
import java.util.HashMap;

public class AdditionalProduct implements Serializable {
    private String name;
    private String count;
    private String fullPrice;
    private String discount;
    private String priceWithDiscount;

    public AdditionalProduct(String name, String count, String fullPrice, String discount, String priceWithDiscount) {
        this.name = name;
        this.count = count;
        this.fullPrice = fullPrice;
        this.discount = discount;
        this.priceWithDiscount = priceWithDiscount;
    }

    public String getName() {
        return name;
    }
    public String getCount() {
        return count;
    }
    public String getFullPrice() {
        return fullPrice;
    }
    public String getDiscount() {
        return discount;
    }
    public String getPriceWithDiscount() {
        return priceWithDiscount;
    }

    @Override
    public String toString() {
        return "AdditionalProduct{" +
                "name='" + name + '\'' +
                ", count='" + count + '\'' +
                ", fullPrice='" + fullPrice + '\'' +
                ", discount='" + discount + '\'' +
                ", priceWithDiscount='" + priceWithDiscount + '\'' +
                '}';
    }
    public StringBuilder dataForSave () {
        StringBuilder data = new StringBuilder();
        data.append("name<b>"+name).append("<a>count<b>"+count).append("<a>fullPrice<b>"+count).append("<a>fullPrice<b>"+fullPrice).
                append("<a>discount<b>"+discount).append("<a>priceWithDiscount<b>"+priceWithDiscount);
        return data;
    }
    public static AdditionalProduct Load(String s){
        String[] data = s.split("<a>");
        String[] tempString = new String[0];
        HashMap<String,String> map = new HashMap();
        for (int i = 0; i < data.length; i++) {
            try {
                tempString = data[i].split("<b>");
                map.put(tempString[0], tempString[1]);
            }catch (Exception e){
                map.put(tempString[0],"");
            }
        }
        AdditionalProduct additionalProduct = new AdditionalProduct(map.get("name"),map.get("count"),map.get("fullPrice"),
                map.get("discount"),map.get("priceWithDiscount"));
        return additionalProduct;

    }
}
