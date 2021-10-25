package org.sydnik.createContract.dataForViber;

import org.sydnik.createContract.data.AdditionalProduct;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
// Заказы нужно отправлять в разные чаты технику в один мойки в другой для этого здесь будет происходить фильтор ИИ
//  для выявление нужного товара и создание текста для заказа.
public class OrderUpSale {
    //все значения с маленькой буквой т.к. при сравнение используются маленькие буквы
    private final String[] WORDS_MAUNFELD = {"maunfeld","фильтр"};
    private final String[] WORDS_EMAR = {"emar","смесител","мойк","емар","avina"};
    public static final int MAUNFELD = 0;
    public static final int EMAR = 1;

    private AdditionalProduct[] additionalProduct;
    private ArrayList<String> goods;
    private String numberContract;


    public OrderUpSale(AdditionalProduct[] additionalProduct,String numberContract) {
        this.additionalProduct = additionalProduct;
        this.numberContract = numberContract;

    }
    public String getTextForOrder(int whatGoods){
        if(whatGoods==MAUNFELD){
            filterMaunfeld();
        }
        else if(whatGoods == EMAR){
            filterEmar();
        }
        return createText();
    }
    public void sendViber(int whatGoods){
        //Вайбер ограничил передачу сообщение 200 символами. В некоторых случаех этого не достаточно:(
        if(whatGoods==MAUNFELD){
            filterMaunfeld();
        }
        else if(whatGoods == EMAR){
            filterEmar();
        }
        URI d = null;
        try {
            d = new URI("viber://forward?text="+preparingForURIFormat(createText()));
            Desktop.getDesktop().browse(d);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void copyTextBuffer(int whatGoods){
        if(whatGoods==MAUNFELD){
            filterMaunfeld();
        }
        else if(whatGoods == EMAR){
            filterEmar();
        }
        String result = createText();
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
    }

    private void filterMaunfeld(){
        try {
            goods = new ArrayList<>();
            for (int i = 0; i < additionalProduct.length; i++) {
                for (int j = 0; j < WORDS_MAUNFELD.length; j++) {
                    if (additionalProduct[i].getName().toLowerCase().contains(WORDS_MAUNFELD[j])) {
                        goods.add(additionalProduct[i].getName() + " " + additionalProduct[i].getCount() + "шт");
                        break;
                    }
                }
            }
        }catch (NullPointerException e){

        }
    }
    private void filterEmar(){
        try {
            goods = new ArrayList<>();
            for (int i = 0; i < additionalProduct.length; i++) {
                for (int j = 0; j < WORDS_EMAR.length; j++) {
                    if (additionalProduct[i].getName().toLowerCase().contains(WORDS_EMAR[j])) {
                        goods.add(additionalProduct[i].getName() + " " + additionalProduct[i].getCount() + "шт");
                        break;
                    }
                }
            }
        }catch (NullPointerException e){

        }
    }
    private String createText(){
        if(goods.size()==0){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(numberContract + "\n");
        for (int i = 0; i < goods.size(); i++) {
            stringBuilder.append(i+1+")"+goods.get(i)+"\n");
        }
        String result = stringBuilder.substring(0,stringBuilder.length()-1);
        return result;
    }
    private String preparingForURIFormat(String line){
        // % = %25    "ПРобел" = %20    \n = %0A     = = %3D    ^ = %5E   ` = %60   { = %7B    | = %7C     } = %7D
        line = line.replaceAll("%","%25");
        line = line.replaceAll(" ","%20");
        line = line.replaceAll("\n","%0A");
        line = line.replaceAll("=","%3D");
//        line = line.replaceAll("^","%5E");// косяк добавляет в начало текста знак зачем?
        line = line.replaceAll("`","%60");
        line = line.replaceAll("\\{","%7B");
        line = line.replaceAll("\\|","%7C");
        line = line.replaceAll("}","%7D");
        return line;
    }
}
