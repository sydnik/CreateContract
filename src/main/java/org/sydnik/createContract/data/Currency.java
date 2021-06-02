package org.sydnik.createContract.data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Currency implements Serializable {
    private double value;
    private String date;
    public static Currency createCurrency(){
        Currency currency = Currency.load();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM YYYY");
        if(currency ==null){
            currency = new Currency();
            currency.save();
        } else if(!currency.getDate().equals(simpleDateFormat.format(new Date()))){
            currency = new Currency();
            currency.save();
        }
        return currency;
    }
    private Currency() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM YYYY");
        date = simpleDateFormat.format(new Date());
        checkKurs();
    }
    public double getValue() {
        return value;
    }
    public String getDate() {
        return date;
    }

    private void checkKurs() {
            String nameSite = "https://www.nbrb.by/API/ExRates/Rates/292";//292 - курс евро.
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
        try {
            FileOutputStream fileOutput = new FileOutputStream("settingManager/currency.dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(this);
            fileOutput.close();
            outputStream.close();
        } catch (Exception e) {
        }
    }
    public static Currency load(){
        try {
            FileInputStream fiStream = new FileInputStream("settingManager/currency.dat");
            ObjectInputStream objectStream = new ObjectInputStream(fiStream);
            Object object = objectStream.readObject();
            fiStream.close();
            objectStream.close();
            return (Currency) object;
        }  catch (Exception e) {
        }
        return null;
    }
}