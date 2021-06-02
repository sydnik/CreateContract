package org.sydnik.createContract.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.Objects;

public class Currency {
    private String name = "Euro" ;
    private int code = 292;
    private double value;
    Date date;

    public Currency() {

    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public void ChechKurs() {
            String nameSite = "http://www.nbrb.by/API/ExRates/Rates/" + code;
            HttpURLConnection connection = null;
            String lineString = "";
            try {
                connection = (HttpURLConnection) new URL(nameSite).openConnection();
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(500);

                connection.connect();
                if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader((connection.getInputStream())));


                    lineString = in.readLine();
                    in.close();
                }
                System.out.println(lineString);
            } catch (Exception e) {
            }
            System.out.println(lineString);
            int i = lineString.lastIndexOf("OfficialRate\":");
            lineString.substring(i + 14, i + 20);
            value = (Double.parseDouble(lineString));
            System.out.println(value);

    }
    public void check () {
    }
}