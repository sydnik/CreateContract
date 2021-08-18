package org.sydnik.createContract.data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Currency implements Serializable {
    final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private double value;
    private String date;
    public static Currency createCurrency(){
        Currency currency = Currency.load();
        if(currency ==null){
            currency = new Currency();
            currency.save();
        } else if(!currency.getDate().equals(SIMPLE_DATE_FORMAT.format(new Date()))){
            currency = new Currency();
            currency.save();
        }
        return currency;
    }
    private Currency() {
        date = SIMPLE_DATE_FORMAT.format(new Date());
        checkKurs();
    }
    public Currency(double rate) {
        date = SIMPLE_DATE_FORMAT.format(new Date());
        value = rate;
        this.save();
    }
    private Currency(String date, double value){
        this.date = date;
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    public String getDate() {
        return date;
    }

    private void checkKurs() {
            String nameSite = "https://www.nbrb.by/API/ExRates/Rates/451";//451 - курс евро.
            HttpURLConnection connection = null;
            String lineString = "";
            try {
                connection = (HttpURLConnection) new URL(nameSite).openConnection();
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                connection.setConnectTimeout(1000);
                connection.setReadTimeout(500);
                connection.connect();
                if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                    lineString = in.readLine();
                    in.close();
                }
            } catch (Exception e) {
            }
            String[] newLine = lineString.split("OfficialRate\":");
            newLine[1] = newLine[1].replace("}","");
            value = (Double.parseDouble(newLine[1]));
    }
    public void save(){
        String path ="settingManager/";
        new File(path).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path+"currency.dat"))){
            String data = "date/=/"+date+"\n"+
                    "value/=/"+value;
            writer.write(data);
        } catch (Exception e) {
        }
    }
    public static Currency load(){
        try (BufferedReader reader = new BufferedReader(new FileReader("settingManager/currency.dat"))){
            HashMap<String,String> map = new HashMap<>();
            String line = "";
            String[] data;
            while (reader.ready()){
                line = reader.readLine();
                data = line.split("/=/");
                map.put(data[0],data[1]);
            }
            return new Currency(map.get("date"),Double.parseDouble(map.get("value")));
        }  catch (Exception e) {
        }
        return null;
    }
}